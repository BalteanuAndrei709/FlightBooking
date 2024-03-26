package com.operatorservice.controller;

import com.operatorservice.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Method that will search for flights with the given criteria.
     * @param from
     * The city from which the user wants to travel.
     * @param to
     * The city to which the user wants to travel.
     * @param departureDate
     * Departure date of the flight.
     * @param returnDate
     * Return date of the flight.
     * @return
     * List of available options that match the given criteria.
     */
    @GetMapping("/{leaving}/{destination}/{departure}/{return}")
    public ResponseEntity<?> searchFlight(@PathVariable(name = "leaving") String leaving,
                                          @PathVariable(name = "destination") String destination,
                                          @PathVariable(name = "departure") String departureDate,
                                          @PathVariable(name = "return") String returnDate) throws ParseException {
        var availableFlights = flightService.findFlights(leaving, destination, departureDate, returnDate);
        return ResponseEntity.status(HttpStatus.OK).body(availableFlights);
    }
}
