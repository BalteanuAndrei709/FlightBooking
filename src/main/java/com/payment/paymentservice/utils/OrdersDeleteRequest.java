package com.payment.paymentservice.utils;

import com.paypal.orders.Order;
import com.paypal.orders.OrderCaptureRequest;
import com.paypal.orders.OrdersCaptureRequest;

import com.paypal.http.*;

import java.util.*;
import java.util.stream.Collectors;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OrdersDeleteRequest extends HttpRequest<Order> {
    public OrdersDeleteRequest(String orderId) {
        super("/v1/checkout/orders/{order_id}", "DELETE", Order.class);
        try {
            path(path().replace("{order_id}", URLEncoder.encode(String.valueOf(orderId), "UTF-8")));
        } catch (UnsupportedEncodingException ignored) {
        }

        header("Content-Type", "application/json");
    }

    public OrdersDeleteRequest authorization(String authorization) {
        header("Authorization", String.valueOf(authorization));
        return this;
    }

    public OrdersDeleteRequest contentType(String contentType) {
        header("Content-Type", String.valueOf(contentType));
        return this;
    }

    public OrdersDeleteRequest payPalAuthAssertion(String payPalAuthAssertion) {
        header("PayPal-Auth-Assertion", String.valueOf(payPalAuthAssertion));
        return this;
    }

    public OrdersDeleteRequest payPalClientMetadataId(String payPalClientMetadataId) {
        header("PayPal-Client-Metadata-Id", String.valueOf(payPalClientMetadataId));
        return this;
    }

    public OrdersDeleteRequest payPalRequestId(String payPalRequestId) {
        header("PayPal-Request-Id", String.valueOf(payPalRequestId));
        return this;
    }

    public OrdersDeleteRequest prefer(String prefer) {
        header("Prefer", String.valueOf(prefer));
        return this;
    }


    public OrdersDeleteRequest requestBody(OrderCaptureRequest orderCaptureRequest) {
        super.requestBody(orderCaptureRequest);
        return this;
    }
}
