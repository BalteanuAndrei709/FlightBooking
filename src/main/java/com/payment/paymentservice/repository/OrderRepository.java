package com.payment.paymentservice.repository;

import com.payment.paymentservice.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderStatus, String> {

    public OrderStatus findByOrderId(String orderId);
}
