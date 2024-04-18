package com.example.BookingServiceUpdated.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class BookingChecks {

    @Field("admin_check")
    private Boolean adminCheck = false;

    @Field("payment_check")
    private Boolean paymentCheck = false;

    // Getters and Setters
    public Boolean getAdminCheck() {
        return adminCheck;
    }

    public void setAdminCheck(Boolean adminCheck) {
        this.adminCheck = adminCheck;
    }

    public Boolean getPaymentCheck() {
        return paymentCheck;
    }

    public void setPaymentCheck(Boolean paymentCheck) {
        this.paymentCheck = paymentCheck;
    }
}