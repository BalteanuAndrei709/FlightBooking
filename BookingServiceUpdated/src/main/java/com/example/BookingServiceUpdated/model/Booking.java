package com.example.BookingServiceUpdated.model;

import jakarta.persistence.Embedded;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "bookings")
public class Booking {

    @Id
    private String id;

    @Field("user_name")
    private String userName;

    @Field("flight_id")
    private Integer flightId;

    @Field("number_of_seats")
    private Integer numberOfSeats;

    @Field("price")
    private Double price;

    @Field("booking_status")
    private BookingStatus bookingStatus;  // Assume BookingStatus is an enum or another class

    @Embedded
    private BookingChecks bookingChecks;

    // Getters and Setters
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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingChecks getBookingChecks() {
        return bookingChecks;
    }

    public void setBookingChecks(BookingChecks bookingChecks) {
        this.bookingChecks = bookingChecks;
    }
}