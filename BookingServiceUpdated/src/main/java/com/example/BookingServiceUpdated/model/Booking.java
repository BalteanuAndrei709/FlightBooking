package com.example.BookingServiceUpdated.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id; // MongoDB typically uses String for IDs

    @Field("user_name")
    private String userName;

    @Field("flight_id")
    private Integer flightId;

    // Uncomment and use if you decide to include bookingDate in the MongoDB document
    // @Field("booking_date")
    // private LocalDate bookingDate;

    @Field("number_of_seats")
    private Integer numberOfSeats;

    @Field("price")
    private Double price;

    @Field("booking_status")
    private BookingStatus bookingStatus;

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

    // Uncomment and use if you decide to include bookingDate in the MongoDB document
    // public LocalDate getBookingDate() {
    //     return bookingDate;
    // }
    //
    // public void setBookingDate(LocalDate bookingDate) {
    //     this.bookingDate = bookingDate;
    // }

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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
