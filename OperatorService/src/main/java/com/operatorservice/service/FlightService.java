package com.operatorservice.service;

import com.operatorservice.dto.FlightDto;
import com.operatorservice.dto.SearchFlightResponseDto;
import com.operatorservice.mapper.FlightMapper;
import com.operatorservice.model.Flight;
import com.operatorservice.repository.FlightRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.InvalidParameterException;
import java.time.LocalDate;

/**
 * Class that contains the business logic related to the flights of an operator.
 */
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    @Value("${operator.name}")
    private String operator;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    /**
     * Method that contains the business logic for registering a new flight to the operator.
     * @param flightDto
     * The flight information.
     * @return
     * The Flight domain object newly created.
     */
    public Mono<Flight> addFlight(FlightDto flightDto) {
        var flight = flightMapper.mapDtoToFlight(flightDto);
        flight.setOperator(operator);
        return flightRepository.save(flight);
    }

    /**
     * Method that contains the business logic for retrieving all incoming flights of the operator.
     * @param operator
     * The name of the operator.
     * @return
     * A Flux that contains the flights done by operator.
     */
    public Flux<Flight> getIncomingFlightsOfOperator(String operator) {
        return flightRepository.existsByOperator(operator)
                .flatMapMany(exists -> {
                    if (!exists) {
                        // If no flights exist, throw an error
                        return Flux.error(new InvalidParameterException("No flights available for this operator"));
                    }
                    // If flights exist, fetch and return them
                    return flightRepository.getFlightsByOperatorAndDepartureDateAfter(operator, LocalDate.now());
                });
    }


    public Mono<SearchFlightResponseDto> searchFlight(String leaving, String destination, LocalDate departureDate, LocalDate returnDate) {

        Flux<Flight> leavingFlights = flightRepository.getFlightsByLeavingAndDestination(
                leaving, destination
        );
        var leavingFlight = leavingFlights
                .reduce((f1, f2) -> f1.getDepartureDate().isBefore(f2.getDepartureDate()) ? f1 : f2);
        return Mono.zip(leavingFlight, leavingFlight, SearchFlightResponseDto::new);

    }
}
