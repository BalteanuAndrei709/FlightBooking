package com.adminservice.dto;

public class BookingAdminStatusDTO {

    private String bookingId;
    private Boolean bookingStatus;

    public BookingAdminStatusDTO(String bookingId, Boolean bookingStatus) {
        this.bookingId = bookingId;
        this.bookingStatus = bookingStatus;
    }

    public BookingAdminStatusDTO() {
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Boolean getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(Boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "BookingAdminStatusDTO{" +
                "bookingId='" + bookingId + '\'' +
                ", bookingStatus=" + bookingStatus +
                '}';
    }
}