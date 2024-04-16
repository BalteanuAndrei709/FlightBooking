package com.notificationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;


@Document("booking")
public class Booking {

    @Id
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "flight_id")
    private Integer flightId;
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;
    private Double price;
    @Column(name = "booking_status")
    private String bookingStatus;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "expiration_time")
    private LocalDate expirationDate;

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
}
