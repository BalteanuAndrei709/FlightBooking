package com.example.BookingServiceUpdated.dto;

import java.time.LocalDate;

public class CompressedBookingDTO {

    private Integer flightId;
    private String userName;
    //private LocalDate bookingDate;
    private Double price;
    private Integer numberOfSeats;
    // Constructors
    public CompressedBookingDTO() {}

    public CompressedBookingDTO(Integer flightId, String userName, LocalDate bookingDate, Double price, Integer numberOfSeats) {
        this.flightId = flightId;
        this.userName = userName;
        //this.bookingDate = bookingDate;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
