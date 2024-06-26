package com.payment.paymentservice.service;

import com.payment.paymentservice.dto.BookingPaymentDTO;
import com.payment.paymentservice.dto.CheckStatusDTO;
import com.payment.paymentservice.exception.OrderNotPayedException;
import com.payment.paymentservice.kafka.producer.KafkaProducerService;
import com.payment.paymentservice.mock.BookingMock;
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
import java.util.Objects;

@Service
public class PayPalService {

    private final String returnUrl;
    private final String cancelUrl;
    private final OrderService orderService;
    private final BusinessPlatformService businessService;
    private final KafkaProducerService kafkaProducerService;
    private final long AVAILABLE_TIME = 10000 * 6; // 1 minutes

    @Autowired
    public PayPalService(@Value("${return.url}") String returnUrl,
                         @Value("${cancel.url}") String cancelUrl,
                         OrderService orderService,
                         BusinessPlatformService businessService,
                         KafkaProducerService kafkaProducerService) {
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.orderService = orderService;
        this.businessService = businessService;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Mono<PaymentOrder> createPayment(BookingPaymentDTO mock) {

        OrderRequest orderRequest = createOrder(mock);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        Mono<BusinessPlatform> mono = businessService.findByIban(mock.getIban());
        Mono<PaymentOrder> paymentMono = mono
                .flatMap(event -> {
                    /**
                     * Genereate the payPalHttpClient based on the clientId and clientSecret fetched using the iban
                     */
                    PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
                    PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);
                    try {
                        HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
                        Order order = orderHttpResponse.result();

                        String redirectUrl = order.links().stream()
                                .filter(link -> link.rel().equals("approve"))
                                .findFirst()
                                .orElseThrow(NoSuchElementException::new)
                                .href();

                        OrderStatus orderStatus = new OrderStatus();
                        orderStatus.setOrderId(order.id());
                        orderStatus.setStatus("INITIATED");
                        orderStatus.setCreationTime(System.currentTimeMillis());
                        orderStatus.setBookingId(mock.getBookingId());
                        orderStatus.setBusinessIban(mock.getIban());
                        orderStatus.setExpirationTime(orderStatus.getCreationTime() + AVAILABLE_TIME);

                        orderService.addOrder(orderStatus).subscribe();

                        PaymentOrder paymentOrder = new PaymentOrder("created", order.id(), redirectUrl);
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + redirectUrl);
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

    /**
     * This method will return only information for a payment that has been completed, otherwise will throw
     * an error.
     */
    public Mono<GetOrder> getOrder(String orderId, String iban) {

        OrdersGetRequest ordersGetRequest = new OrdersGetRequest(orderId);
        Mono<BusinessPlatform> monoBusiness = businessService.findByIban(iban);

        Mono<GetOrder> orderMono = monoBusiness.flatMap(event -> {

            PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
            PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);

            try {
                HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersGetRequest);
                Order order = httpResponse.result();
                GetOrder getOrderObj = buildGetOrder(order);
                return Mono.just(getOrderObj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return orderMono;
    }

    /**
     * @param token represents the orderId that was generated by PayPal during the creation of the order
     * @param iban  represents the operator from which we buy ticket.
     *              <p>
     *              This method is used to finalise the payment process by capturing the order that was paid by the customer.
     */
    public Mono<CompletedOrder> captureOrder(String token, String iban) {

        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        Mono<BusinessPlatform> monoBusiness = businessService.findByIban(iban);
        Mono<OrderStatus> orderStatusMono = orderService.findByOrderId(token);
        long callTime = System.currentTimeMillis();

        Mono<CompletedOrder> resultMono = orderStatusMono.flatMap(orderStatus -> {
            CheckStatusDTO checkStatusDTO = new CheckStatusDTO();
            if (callTime > orderStatus.getExpirationTime() && !orderStatus.getStatus().equals("SUCCESS")) {
                orderStatus.setStatus("CANCELED");
                checkStatusDTO.setBookingId(orderStatus.getBookingId());
                checkStatusDTO.setStatus(false);
                kafkaProducerService.sendMessage(checkStatusDTO);
                orderService.updateOrder(orderStatus, token).subscribe();
                return Mono.just(new CompletedOrder(orderStatus.getStatus(), orderStatus.getId()));
            } else {
                monoBusiness.subscribe(event -> {

                    PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
                    PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);
                    try {
                        HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
                        Order order = httpResponse.result();
                        orderStatus.setStatus("SUCCESS");
                        orderService.updateOrder(orderStatus, order.id()).subscribe();
                        checkStatusDTO.setBookingId(orderStatus.getBookingId());
                        checkStatusDTO.setStatus(true);
                        kafkaProducerService.sendMessage(checkStatusDTO);
                    } catch (IOException e) {
                        throw new OrderNotPayedException("Payment with id " + token + " was not paid by following the given link");
                    }
                });

                return Mono.just(new CompletedOrder(orderStatus.getStatus(), orderStatus.getId()));
            }
        });

        return resultMono;
    }

    private OrderRequest createOrder(BookingPaymentDTO mock) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        /**
         * AmountWithBreakdown = breakdown of the amount in the value of the item, taxes, shipping, discount, etc
         */
        AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown();
        amountWithBreakdown.currencyCode("EUR");
        amountWithBreakdown.value(mock.getPrice().toString());

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
        item.name("Flight from X to Y");
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
        return orderRequest;
    }

    private GetOrder buildGetOrder(Order order) {
        GetOrder getOrderObj = new GetOrder();
        getOrderObj.setPayee(order.purchaseUnits().get(0).payee());
        getOrderObj.setPayer(order.payer());

        getOrderObj.setPayerId(getOrderObj.getPayer().payerId());
        getOrderObj.setPayerEmail(getOrderObj.getPayer().email());

        getOrderObj.setPayeeId(getOrderObj.getPayee().merchantId());
        getOrderObj.setPayeeEmail(getOrderObj.getPayee().email());
        return getOrderObj;
    }
}
