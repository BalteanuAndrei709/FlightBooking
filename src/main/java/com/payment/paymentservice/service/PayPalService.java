package com.payment.paymentservice.service;

import com.payment.paymentservice.model.*;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PayPalService {

    private final String returnUrl;
    private final String cancelUrl;
    private final OrderService orderService;
    private final BusinessPlatformService businessService;

    @Autowired
    public PayPalService(@Value("${return.url}") String returnUrl,
                         @Value("${cancel.url}") String cancelUrl,
                         OrderService orderService,
                         BusinessPlatformService businessService) {
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.orderService = orderService;
        this.businessService = businessService;

    }

    public Mono<PaymentOrder> createPayment(Double payAmount) {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        /**
         * AmountWithBreakdown = breakdown of the amount in the value of the item, taxes, shipping, discount, etc
         */
        AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown();
        amountWithBreakdown.currencyCode("EUR");
        amountWithBreakdown.value(payAmount.toString());

        /**
         * PurchaseUnitRequest = The most important attributes are amount (required) and array of items.
         */
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
        purchaseUnitRequest.amountWithBreakdown(amountWithBreakdown);

        Money money = new Money();
        money.currencyCode(amountWithBreakdown.currencyCode());
        money.value(amountWithBreakdown.value());

        AmountBreakdown amountBreakdown = new AmountBreakdown();
        amountBreakdown.itemTotal(money);
        amountWithBreakdown.amountBreakdown(amountBreakdown);

        Item item = new Item();
        item.category("DIGITAL_GOODS");
        item.quantity("1");
        item.name("Flight");
        item.description("Flight");
        item.unitAmount(money);

        purchaseUnitRequest.items(List.of(item));
        purchaseUnitRequest.amountWithBreakdown().amountBreakdown().itemTotal(money);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.returnUrl(returnUrl);
        applicationContext.cancelUrl(cancelUrl);
        applicationContext.userAction("PAY_NOW");

        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        Mono<BusinessPlatform> mono = businessService.findByIban("RO86TRM");
        // aici se poate baga o verificare daca in mono avem ceva

        Mono<PaymentOrder> paymentMono = mono
                .flatMap(event -> {

                    PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
                    PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);
                    try {
                        HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
                        Order order = orderHttpResponse.result();

                        /**
                         * The payer (the customer) can be linked with the iban or something from the booking topic (kafka) or database.
                         */
                        String redirectUrl = order.links().stream()
                                .filter(link -> link.rel().equals("approve"))
                                .findFirst()
                                .orElseThrow(NoSuchElementException::new)
                                .href();

                        //System.out.println(order.status());

                        OrderStatus orderStatus = new OrderStatus();
                        orderStatus.setOrderId(order.id());
                        orderStatus.setStatus("INITIATED");
                        orderService.addOrder(orderStatus).subscribe();

                        PaymentOrder paymentOrder = new PaymentOrder("success", order.id(), redirectUrl);
                        return Mono.just(paymentOrder);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        PaymentOrder paymentOrder = new PaymentOrder();
                        paymentOrder.setStatus("Error");
                        return Mono.just(paymentOrder);
                    }
                });
        return paymentMono;
    }

    public Mono<CompletedOrder> completePayment(String token) {

        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        Mono<BusinessPlatform> monoBusiness = businessService.findByIban("RO86TRM");

        Mono<CompletedOrder> completedOrderMono = monoBusiness.flatMap(event -> {

            PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
            PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);
            Order order = new Order();

            try {
                HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
                order = httpResponse.result();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            if (order.status() != null) { // in cazul asta va avea mereu COMPLETED daca plata a fost efectuata cu succes
                final String orderId = order.id();
                Mono<OrderStatus> mono = orderService.findByOrderId(orderId);
                System.out.println(order.status());

                mono.subscribe(e -> {
                    e.setStatus("SUCCESS");
                    orderService.updateOrder(e, orderId).subscribe();
                });
                return Mono.just(new CompletedOrder(order.status(), token));
            } else {
                System.out.println(order.status());
                Mono<OrderStatus> mono = orderService.findByOrderId(token);

                mono.subscribe(e -> {
                    e.setStatus("CANCELED");
                    orderService.updateOrder(e, e.getOrderId()).subscribe();
                });
                return Mono.just(new CompletedOrder("canceled", token));
            }

        });

        return completedOrderMono;
    }

    /*public GetOrder getOrder(String orderId) {
        OrdersGetRequest ordersGetRequest = new OrdersGetRequest(orderId);

        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersGetRequest);
            Order order = httpResponse.result();
            GetOrder getOrderObj = new GetOrder();
            getOrderObj.setPayee(order.purchaseUnits().get(0).payee());
            getOrderObj.setPayer(order.payer());

            getOrderObj.setPayerId(getOrderObj.getPayer().payerId());
            getOrderObj.setPayerEmail(getOrderObj.getPayer().email());

            getOrderObj.setPayeeId(getOrderObj.getPayee().merchantId());
            getOrderObj.setPayeeEmail(getOrderObj.getPayee().email());

            return getOrderObj;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

}
