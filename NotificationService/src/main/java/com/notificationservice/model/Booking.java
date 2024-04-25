package com.notificationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.Date;


@Document(indexName = "booking_audit")
public class Booking {

    @Id
    private String id;
  //  @Field(type = FieldType.Text, name = "user_name")
    private String userName;
   // @Field(type = FieldType.Integer, name = "flight_id")
    private Integer flightId;
   // @Field(type = FieldType.Integer, name = "number_of_seats")
    private Integer numberOfSeats;
   // @Field(type = FieldType.Double, name = "price")
    private Double price;
   // @Field(type = FieldType.Text, name = "booking_status")
    private String bookingStatus;
    //@Field(type = FieldType.Text, name = "user_email")
    private String userEmail;
    //@Field(type = FieldType.Date, name = "expiration_date")
    private LocalDate expirationDate;

    public Booking() {
    }

    public Booking(String id, String userName, Integer flightId, Integer numberOfSeats, Double price, String bookingStatus, String userEmail, LocalDate expirationDate) {
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

}
