package com.payment.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents the status of an order, from its creation until it has been either paid with success or canceled.
 * First, when an order has been created, it will be saved with the status "INITIALIZED". Then, it will either be updated with
 * the status "SUCCESS" or "CANCELED", depending on if the order has been paid successfully or not.
 */
@Document
public class OrderStatus {
    @Id
    private String id;
    private String orderId;
    private String status;
    private Long creationTime;
    private Long expirationTime;
    private String bookingId;
    private String businessIban;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    private String bookingId;

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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBusinessIban() {
        return businessIban;
    }

    public void setBusinessIban(String businessIban) {
        this.businessIban = businessIban;
    }

    public OrderStatus(String id, String orderId, String status, Long creationTime, Long expirationTime, String bookingId, String businessIban) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
        this.bookingId = bookingId;
        this.businessIban = businessIban;
    }

    public OrderStatus() {
    }
}
