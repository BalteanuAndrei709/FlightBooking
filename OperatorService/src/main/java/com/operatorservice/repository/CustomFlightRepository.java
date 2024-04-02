package com.operatorservice.repository;

import com.operatorservice.model.Flight;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

/**
 * Interface that contains the method for searching for a custom flight.
 */
public interface CustomFlightRepository {

    /**
     * Method that search for custom flights.
     * @param leaving
     * The city from which the flight takes off.
     * @param destination
     * The city in which the flight will arrive.
     * @param departureDate
     * The date when the flight departures.
     * @param returnDate
     * The date when the user want to return.
     * @return
     * Flights the match the given criteria.
     */
    Flux<Flight> findLeavingFlights(String operator, String leaving, String destination,
                                    LocalDate departureDate, LocalDate returnDate);
    /**
     * Method that search for custom flights.
     * @param leaving
     * The city from which the flight takes off.
     * @param destination
     * The city in which the flight will arrive.
     * @param departureDate
     * The date when the flight departures.
     * @param returnDate
     * The date when the user want to return.
     * @return
     * Flights the match the given criteria.
     */
    Flux<Flight> findReturningFlights(String operator, String leaving, String destination,
                                    LocalDate departureDate, LocalDate returnDate);
}