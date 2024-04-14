package com.adminservice.dto;

import com.adminservice.model.BookingStatus;

public class BookingStatusDTO {

    private String id;
    private BookingStatus bookingStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    // Constructors
    public BookingStatusDTO() {
    }

    public BookingStatusDTO(String bookingId, BookingStatus bookingStatus) {
        this.id = bookingId;
        this.bookingStatus = bookingStatus;
    }
}