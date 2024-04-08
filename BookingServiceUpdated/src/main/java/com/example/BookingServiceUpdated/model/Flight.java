package com.example.BookingServiceUpdated.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String destination;
    private LocalDate departureDate;
    private int numberSeatsAvailable;

    // Constructors, getters, and setters

    public Flight() {
    }

    // Add getters and setters for all properties
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfSeatsAvailable() {
        return numberSeatsAvailable;
    }

    public void setNumberOfSeatsAvailable(int numberSeatsAvailable) {
        this.numberSeatsAvailable = numberSeatsAvailable;
    }
}

