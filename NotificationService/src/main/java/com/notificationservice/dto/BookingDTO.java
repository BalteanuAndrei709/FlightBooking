package com.notificationservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.Date;

public class BookingDTO {

    private String id;
    private String userName;
    private Integer flightId;
    private Integer numberOfSeats;
    private Double price;
    private String bookingStatus;
    private String userEmail;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expirationDate;

    public BookingDTO() {
    }

    public BookingDTO(String id, String userName, Integer flightId, Integer numberOfSeats, Double price, String bookingStatus, String userEmail, LocalDate expirationDate) {
        this.id = id;
        this.userName = userName;
        this.flightId = flightId;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.bookingStatus = bookingStatus;
        this.userEmail = userEmail;
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", flightId=" + flightId +
                ", numberOfSeats=" + numberOfSeats +
                ", price=" + price +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
