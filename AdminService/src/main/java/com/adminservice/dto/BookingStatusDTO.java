package com.adminservice.dto;

import com.adminservice.model.BookingStatus;

public class BookingStatusDTO {

    private Integer id;
    private BookingStatus bookingStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BookingStatusDTO(Integer bookingId, BookingStatus bookingStatus) {
        this.id = bookingId;
        this.bookingStatus = bookingStatus;
    }
}