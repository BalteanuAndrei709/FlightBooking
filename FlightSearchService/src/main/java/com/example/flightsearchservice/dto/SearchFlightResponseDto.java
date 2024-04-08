package com.example.flightsearchservice.dto;

import com.example.flightsearchservice.model.Flight;
import java.util.List;



public class SearchFlightResponseDto  {

    private final List<Flight> leavingFlight;
    private final List<Flight> returningFlight;

    public SearchFlightResponseDto(List<Flight> leavingFlight, List<Flight> returningFlight) {
        this.leavingFlight = leavingFlight;
        this.returningFlight = returningFlight;
    }

    public List<Flight> getLeavingFlight() {
        return leavingFlight;
    }

    public List<Flight> getReturningFlight() {
        return returningFlight;
    }


}
