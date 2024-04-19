package com.example.BookingServiceUpdated.dto;

public class BookingAdminDTO {

    private String bookingId;
    private Integer flightId;
    private int numberOfSeats;

    public BookingAdminDTO(String bookingId, int numberOfSeats, Integer flightId) {
        this.flightId = flightId;
        this.bookingId = bookingId;
        this.numberOfSeats = numberOfSeats;
    }

    public BookingAdminDTO() {}

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
