package com.payment.paymentservice.model;

import java.io.Serializable;

/**
 * This model is used to represent the order that was captured.
 * It uses the payId generated after creating a new payment to identify it inside PayPal.
 */
public class CompletedOrder {
    private String status;
    private String payId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public CompletedOrder(String status, String payId) {
        this.status = status;
        this.payId = payId;
    }

    public CompletedOrder() {
    }

    public CompletedOrder(String status) {
        this.status = status;
    }

}
