package com.payment.paymentservice.model;

import java.io.Serializable;

/**
 * Model used to test the capturing of the payee and payer information
 */
public class GetOrder implements Serializable {
    String payerId;
    String payerEmail;
    String payeeId;
    String payeeEmail;
    String orderStatus;


    public GetOrder(String payerId, String payerEmail, String payeeId, String payeeEmail, String orderStatus) {
        this.payerId = payerId;
        this.payerEmail = payerEmail;
        this.payeeId = payeeId;
        this.payeeEmail = payeeEmail;
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public GetOrder() {
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }
}
