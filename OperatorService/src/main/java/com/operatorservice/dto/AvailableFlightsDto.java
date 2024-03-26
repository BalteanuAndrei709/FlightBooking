package com.operatorservice.dto;

import com.operatorservice.model.Flight;

import java.util.List;

public class AvailableFlightsDto {

    private List<Flight> availableForLeaving;
    private List<Flight> availableForReturning;

    public AvailableFlightsDto(List<Flight> availableForLeaving, List<Flight> availableForReturning) {
        this.availableForLeaving = availableForLeaving;
        this.availableForReturning = availableForReturning;
    }

    public List<Flight> getAvailableForLeaving() {
        return availableForLeaving;
    }

    public void setAvailableForLeaving(List<Flight> availableForLeaving) {
        this.availableForLeaving = availableForLeaving;
    }

    public List<Flight> getAvailableForReturning() {
        return availableForReturning;
    }

    public void setAvailableForReturning(List<Flight> availableForReturning) {
        this.availableForReturning = availableForReturning;
    }
}
