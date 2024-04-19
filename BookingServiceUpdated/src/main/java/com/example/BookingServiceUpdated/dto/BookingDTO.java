package com.example.BookingServiceUpdated.dto;

import java.time.LocalDate;

public class BookingDTO {

    private String id;
    private Integer flightId;
    private String email;
    //private LocalDate bookingDate;
    private Double price;
    private Integer numberOfSeats;
    // Constructors
    public BookingDTO() {}

    public BookingDTO(String id, Integer flightId, String email, LocalDate bookingDate, Double price, Integer numberOfSeats) {
        this.id = id;
        this.flightId = flightId;
        this.email = email;
        //this.bookingDate = bookingDate;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public LocalDate getBookingDate() {
//        return bookingDate;
//    }
//
//    public void setBookingDate(LocalDate bookingDate) {
//        this.bookingDate = bookingDate;
//    }

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
