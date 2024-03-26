package com.searchflight.dto;

import java.util.List;

public class AvailableFlightsDto {

    private List<FlightDto> availableForLeaving;
    private List<FlightDto> availableForReturning;

    public AvailableFlightsDto(List<FlightDto> availableForLeaving, List<FlightDto> availableForReturning) {
        this.availableForLeaving = availableForLeaving;
        this.availableForReturning = availableForReturning;
    }

    public List<FlightDto> getAvailableForLeaving() {
        return availableForLeaving;
    }

    public void setAvailableForLeaving(List<FlightDto> availableForLeaving) {
        this.availableForLeaving = availableForLeaving;
    }

    public List<FlightDto> getAvailableForReturning() {
        return availableForReturning;
    }

    public void setAvailableForReturning(List<FlightDto> availableForReturning) {
        this.availableForReturning = availableForReturning;
    }
}
