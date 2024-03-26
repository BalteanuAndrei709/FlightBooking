package com.searchflight.service;

import com.searchflight.dto.AvailableFlightsDto;
import com.searchflight.model.Flight;
import com.searchflight.model.Operator;
import com.searchflight.repository.FlightRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service layer for the SearchFlight service.
 */
@Service
public class SearchFlightService {

    //The repository layer for the Flight entity.
    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;

    public SearchFlightService(FlightRepository flightRepository, RestTemplate restTemplate) {
        this.flightRepository = flightRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * Method that contains the business logic for searching a flight
     * @param leaving
     * The city from which the user wants to travel.
     * @param destination
     * The city to which the user wants to travel.
     * @param departureDate
     * Departure date of the flight.
     * @param returnDate
     * Return date of the flight.
     * @return
     * List of available options that match the given criteria.
     */
    public List<Object> findFlights(String leaving, String destination, String departureDate, String returnDate) {
        // extract all operators which do the flight
        var operators = findOperators(leaving,destination);
         /*
          * For every operator that registered the flight that the user wants to take, we call the external
          * API in order to check if there are any flights that match the range date added by the user.
          */
        var matchingFlightsFromAllOperators = new ArrayList<>();
        operators.forEach(operator -> {
            Map<String, String> params = new HashMap<>();
            params.put("leaving", leaving);
            params.put("destination", destination);
            params.put("departure-date", departureDate);
            params.put("return-date", returnDate);

            ResponseEntity<AvailableFlightsDto> response = restTemplate.getForEntity(
                    operator.getApiSearch(),
                    AvailableFlightsDto.class,
                    params
            );
            matchingFlightsFromAllOperators.add(response.getBody());
        });

        return matchingFlightsFromAllOperators;
    }

    /**
     * Extract the operators which have the flight that match the details.
     * @param leaving
     * The city from which the user wants to travel.
     * @param destination
     * The city to which the user wants to travel.
     * @return
     * The list of operators who have the flights with given details.
     */
    private List<Operator> findOperators(String leaving, String destination){
        var matchingFlights = flightRepository.findAllByLeavingAndDestination(leaving,destination);
        return matchingFlights.stream()
                .map(Flight::getOperator)
                .toList();
    }
}
