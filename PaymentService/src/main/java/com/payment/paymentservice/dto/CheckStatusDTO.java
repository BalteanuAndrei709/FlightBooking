package com.payment.paymentservice.dto;

public class CheckStatusDTO {

    private String bookingId;
    private Boolean status;

    public CheckStatusDTO() {
    }

    public CheckStatusDTO(String bookingId) {
        this.bookingId = bookingId;
    }

    public CheckStatusDTO(String bookingId, Boolean status) {
        this.bookingId = bookingId;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}