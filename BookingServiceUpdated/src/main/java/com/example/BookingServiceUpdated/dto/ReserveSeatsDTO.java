package com.example.BookingServiceUpdated.dto;

public class ReserveSeatsDTO {

    private String bookingId;
    private Integer flightId;
    private int numberOfSeats;

    public ReserveSeatsDTO(String bookingId, int numberOfSeats, Integer flightId) {
        this.flightId = flightId;
        this.bookingId = bookingId;
        this.numberOfSeats = numberOfSeats;
    }

    public ReserveSeatsDTO() {}

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
