package com.operatorservice.dto;

import com.operatorservice.model.Flight;
public class SearchFlightResponseDto {

    private final Flight leavingFlight;
    private final Flight returningFlight;

    public Flight getLeavingFlight() {
        return leavingFlight;
    }

    public Flight getReturningFlight() {
        return returningFlight;
    }

    public SearchFlightResponseDto(Flight leavingFlight, Flight returningFlight) {
        this.leavingFlight = leavingFlight;
        this.returningFlight = returningFlight;
    }
}
