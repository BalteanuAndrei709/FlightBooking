package com.operatorservice.dto;

import com.operatorservice.model.Flight;
import reactor.core.publisher.Flux;

import java.util.List;

public class SearchFlightResponseDto {

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

    @Override
    public String toString() {
        return "SearchFlightResponseDto{" +
                "leavingFlight=" + leavingFlight +
                ", returningFlight=" + returningFlight +
                '}';
    }
}
