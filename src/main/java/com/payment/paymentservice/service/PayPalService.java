package com.payment.paymentservice.service;

import com.payment.paymentservice.model.CompletedOrder;
import com.payment.paymentservice.model.PaymentOrder;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PayPalService {

    private final PayPalHttpClient payPalHttpClient;

    @Autowired
    public PayPalService(PayPalHttpClient payPalHttpClient) {
        this.payPalHttpClient = payPalHttpClient;
    }

    public PaymentOrder createPayment(BigDecimal payAmount) {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        AmountWithBreakdown amount = new AmountWithBreakdown();
        amount.currencyCode("EUR");
        amount.value(payAmount.toString());

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
        purchaseUnitRequest.amountWithBreakdown(amount);

        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.returnUrl("http://localhost:8080/payment/success");
        applicationContext.cancelUrl("http://localhost:8080/payment/cancel");

        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();

            String redirectUrl = order.links().stream()
                    .filter(link -> link.rel().equals("approve"))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

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
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null) {
                return new CompletedOrder("success", token);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new CompletedOrder("error");
    }

}
