package com.adminservice.dto;

import com.adminservice.model.BookingStatus;

import java.time.LocalDate;

public class BookingDTO {

    private String id;
    private Integer flightId;
    private String userName;
    private LocalDate bookingDate;
    private Double price;
    private Integer numberOfSeats;
    private BookingStatus bookingStatus;

    // Constructors
    public BookingDTO() {}

    public BookingDTO(String id, Integer flightId, String userName, LocalDate bookingDate, Double price, Integer numberOfSeats, BookingStatus bookingStatus) {
        this.id = id;
        this.flightId = flightId;
        this.userName = userName;
        this.bookingDate = bookingDate;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.bookingStatus = bookingStatus;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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