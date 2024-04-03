package com.operatorservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Dtp for a flight.
 */
public class FlightDto {
    private LocalDate departureDate;
    private String departureTime;
    private String leaving;
    private String destination;
    private Float price;

    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public String getLeaving() {
        return leaving;
    }
    public String getDestination() {
        return destination;
    }
    public Float getPrice() {
        return price;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setLeaving(String leaving) {
        this.leaving = leaving;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
