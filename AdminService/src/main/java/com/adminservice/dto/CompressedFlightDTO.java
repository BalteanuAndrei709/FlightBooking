package com.adminservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompressedFlightDTO {

    private String leaving;
    private String destination;
    private Integer numberSeatsTotal;
    private LocalDate dateOfDeparture;
    private LocalTime timeOfDeparture;

    public String getLeaving() {
        return leaving;
    }

    public void setLeaving(String leaving) {
        this.leaving = leaving;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getNumberSeatsTotal() {
        return numberSeatsTotal;
    }

    public void setNumberSeatsTotal(Integer numberSeatsTotal) {
        this.numberSeatsTotal = numberSeatsTotal;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }
}
