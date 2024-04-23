package com.adminservice.dto;

public class BookingAdminStatusDTO {

    private String bookingId;
    private Boolean status;

    public BookingAdminStatusDTO() {
    }

    public BookingAdminStatusDTO(String bookingId, Boolean status) {
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