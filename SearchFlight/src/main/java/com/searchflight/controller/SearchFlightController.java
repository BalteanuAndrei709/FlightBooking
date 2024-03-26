package com.searchflight.controller;

import com.searchflight.service.SearchFlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling all HTTP request related to search flight.
 */
@RestController
@RequestMapping("/api/search-flight")
public class SearchFlightController {

    // layer that contains the business logic for the SearchFlight service
    private final SearchFlightService searchFlightService;

    public SearchFlightController(SearchFlightService searchFlightService) {
        this.searchFlightService = searchFlightService;
    }

    /**
     * Method that will handle the initial request regarding the search of flights.
     * @param leaving
     * The city from which the user wants to travel.
     * @param arriving
     * The city to which the user wants to travel.
     * @param departureDate
     * Departure date of the flight.
     * @param returnDate
     * Return date of the flight.
     * @return
     * List of available options that match the given criteria.
     */
    @GetMapping("/{leaving}/{arriving}/{departure}/{return}")
    public ResponseEntity<?> searchFlight(@PathVariable(name = "leaving") String leaving,
                                          @PathVariable(name = "arriving") String arriving,
                                          @PathVariable(name = "departure") String departureDate,
                                          @PathVariable(name = "return") String returnDate){
        var availableOptions = searchFlightService.findFlights(leaving, arriving, departureDate, returnDate);
        return ResponseEntity.status(HttpStatus.OK).body(availableOptions);
    }
}
