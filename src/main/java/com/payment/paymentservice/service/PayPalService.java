package com.payment.paymentservice.service;

import com.payment.paymentservice.model.CompletedOrder;
import com.payment.paymentservice.model.GetOrder;
import com.payment.paymentservice.model.OrderStatus;
import com.payment.paymentservice.model.PaymentOrder;
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

    private final PayPalHttpClient payPalHttpClient;

    private final String returnUrl;

    private final String cancelUrl;

    private final OrderService orderService;


    @Autowired
    public PayPalService(PayPalHttpClient payPalHttpClient,
                         @Value("${return.url}") String returnUrl,
                         @Value("${cancel.url}") String cancelUrl,
                         OrderService orderService) {
        this.payPalHttpClient = payPalHttpClient;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.orderService = orderService;

    }

    public PaymentOrder createPayment(Double payAmount) {

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


            return new PaymentOrder("success", order.id(), redirectUrl);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setStatus("Error");
            return paymentOrder;
        }
    }

    public CompletedOrder completePayment(String token) {
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        Order order = new Order();
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            order = httpResponse.result();
            System.out.println(order.status());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (order.status() != null) { // in cazul asta va avea mereu COMPLETED daca plata a fost efectuata cu succes
            final String orderId = order.id();
            Mono<OrderStatus> mono = orderService.findByOrderId(orderId);

            mono.subscribe(e -> {
                e.setStatus("SUCCESS");
                orderService.updateOrder(e, orderId).subscribe();
            });
            return new CompletedOrder(order.status(), token);
        } else {
            Mono<OrderStatus> mono = orderService.findByOrderId(token);
            mono.subscribe(e -> {
                e.setStatus("CANCELED");
                orderService.updateOrder(e, e.getOrderId()).subscribe();
            });
            return new CompletedOrder("canceled", token);
        }
    }

    public GetOrder getOrder(String orderId) {
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
    }

}
