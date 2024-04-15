package com.payment.paymentservice.dto;

import com.payment.paymentservice.model.BookingStatus;


public class BookingDTO {

    private String id;
    private Integer flightId;
    private String userName;
    //private LocalDate bookingDate;
    private Double price;
    private Integer numberOfSeats;
    private BookingStatus bookingStatus;
    // Constructors
    public BookingDTO() {}

    public BookingDTO(String id, Integer flightId, String userName, Double price, Integer numberOfSeats) {
        this.id = id;
        this.flightId = flightId;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public LocalDate getBookingDate() {
//        return bookingDate;
//    }
//
//    public void setBookingDate(LocalDate bookingDate) {
//        this.bookingDate = bookingDate;
//    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
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
