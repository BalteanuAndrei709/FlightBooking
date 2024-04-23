package com.payment.paymentservice.controller;

import com.payment.paymentservice.model.OrderStatus;
import com.payment.paymentservice.service.OrderService;
import com.payment.paymentservice.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;


@Controller
public class ViewController {

    private final PayPalService payPalService;
    private final OrderService orderService;

    @Autowired
    public ViewController(PayPalService payPalService, OrderService orderService) {
        this.payPalService = payPalService;
        this.orderService = orderService;
    }

    @GetMapping("payment/success")
    public String successPayment(ServerHttpRequest serverHttpRequest) {
        MultiValueMap<String, String> queryParams = serverHttpRequest.getQueryParams();
        String token = queryParams.getFirst("token");

        Mono<OrderStatus> orderMono = orderService.findByOrderId(token);
        orderMono.subscribe(event -> {
            payPalService.captureOrder(event.getOrderId(), event.getBusinessIban()).subscribe();
        });
        return "success";
    }

    @GetMapping("/payment/cancel")
    public String cancelPayment() {
        return "cancel";
    }
}
