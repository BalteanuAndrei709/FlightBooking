package com.operatorservice.controller;

import com.operatorservice.service.OperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * Controller class responsible for handling all HTTP request related to an operator.
 */
@RestController
@RequestMapping("/api/flight")
public class OperatorController {

    // Service layer for the operator.
    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    /**
     * Method that will search for flights with the given criteria.
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
    @GetMapping("/{leaving}/{arriving}/{departure-date}/{return-date}")
    public ResponseEntity<?> searchFlight(@PathVariable(name = "leaving") String leaving,
                                          @PathVariable(name = "arriving") String arriving,
                                          @PathVariable(name = "departure-date") String departureDate,
                                          @PathVariable(name = "return-date") String returnDate) throws ParseException {
        var availableFlights = operatorService.findFlights(leaving, arriving, departureDate, returnDate);
        return ResponseEntity.status(HttpStatus.OK).body(availableFlights);
    }
}
