package com.searchflight.service;

import com.searchflight.model.Flight;
import com.searchflight.model.Operator;
import com.searchflight.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFlightService {

    private final FlightRepository flightRepository;

    public SearchFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Method that contains the business logic for searching a flight
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
    public List<Object> findFlights(String from, String to, String departureDate, String returnDate) {
        var operators = findOperators(from,to);

        //check daca mai sunt bilete disponibile
        operators.forEach(operator -> {
            // operator.searchApi()...
        });

        return null;
    }

    /**
     * Extract the operators which have the flight that match the details.
     * @param from
     * The city from which the user wants to travel.
     * @param to
     * The city to which the user wants to travel.
     * @return
     * The list of operators who have the flights with given details.
     */
    private List<Operator> findOperators(String from, String to){
        var matchingFlights = flightRepository.findAllByLeavingAndArriving(from,to);
        return matchingFlights.stream()
                .map(Flight::getOperator)
                .toList();
    }
}
