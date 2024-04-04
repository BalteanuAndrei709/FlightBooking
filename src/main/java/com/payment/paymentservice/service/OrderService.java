package com.payment.paymentservice.service;

import com.payment.paymentservice.model.OrderStatus;
import com.payment.paymentservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(OrderStatus orderStatus) {
        orderRepository.save(orderStatus);
    }

    public OrderStatus findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public void updateOrder(OrderStatus orderStatus, String orderId) {
        OrderStatus existingOrderStatus = orderRepository.findByOrderId(orderId);
        existingOrderStatus.setStatus(orderStatus.getStatus());
        orderRepository.save(existingOrderStatus);
    }

}
