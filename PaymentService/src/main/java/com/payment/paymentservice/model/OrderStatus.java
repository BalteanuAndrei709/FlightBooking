package com.payment.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document
public class OrderStatus {
    @Id
    private String id;
    private String orderId;
    private String status;
    private Long creationTime;
    private Long expirationTime;

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderStatus(String id, String orderId, String status, Long creationTime, Long expirationTime) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
    }

    public OrderStatus() {
    }
}
