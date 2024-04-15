package com.payment.paymentservice.model;

/**
 * Represents the order that has been created. If the order has been created with no problem, then it will return a status "success",
 * alongside with the orderId and the url that the user must acces to pay the amount via PayPal.
 */
public class PaymentOrder {

    private String status;
    private String orderId;
    private String redirectUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public PaymentOrder() {
    }

    public PaymentOrder(String status, String payId, String redirectUrl) {
        this.status = status;
        this.orderId = payId;
        this.redirectUrl = redirectUrl;
    }
}
