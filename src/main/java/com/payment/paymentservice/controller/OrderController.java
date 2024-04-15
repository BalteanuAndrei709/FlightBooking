package com.payment.paymentservice.controller;

import com.payment.paymentservice.model.OrderStatus;
import com.payment.paymentservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Mono<OrderStatus> getOrderById(@PathVariable(name = "id") String orderId) {
        return orderService.findByOrderId(orderId);
    }

}
