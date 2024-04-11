package com.example.BookingServiceUpdated.dto;

import com.example.BookingServiceUpdated.model.BookingStatus;

import java.time.LocalDate;

public class BookingDTO {

    private Integer id;
    private Integer flightId;
    private String userName;
    //private LocalDate bookingDate;
    private Double price;
    private Integer numberOfSeats;
    private BookingStatus bookingStatus;
    // Constructors
    public BookingDTO() {}

//    @Override
//    public String toString() {
//        return "BookingDTO{" +
//                "id=" + id +
//                ", flightId=" + flightId +
//                ", userName='" + userName + '\'' +
//                ", bookingDate=" + bookingDate +
//                ", price=" + price +
//                ", numberOfSeats=" + numberOfSeats +
//                '}';
//    }

    public BookingDTO(Integer id, Integer flightId, String userName, LocalDate bookingDate, Double price, Integer numberOfSeats) {
        this.id = id;
        this.flightId = flightId;
        this.userName = userName;
        //this.bookingDate = bookingDate;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
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
