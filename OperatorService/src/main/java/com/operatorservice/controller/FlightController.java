package com.operatorservice.controller;

import com.operatorservice.dto.FlightDto;
import com.operatorservice.dto.SearchFlightResponseDto;
import com.operatorservice.model.Flight;
import com.operatorservice.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * Controller class that will handle all HTTP request related to the flights of an operator.
 */
@RestController
@RequestMapping("/api/v1.0/flights")
public class FlightController {

    // service layer that contains the business logic related to the flight of an operator
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Method that handles the HTTP Post request for adding a flight.
     * {@code Mono<ResponseEntity<FlightDto>>} will provide response status, header and bode async
     * @param flightDto
     * The information about the flight the user wants to add.
     * @return
     * The newly created flight.
     */
    @PostMapping
    public Mono<ResponseEntity<Flight>> addFlight(@RequestBody FlightDto flightDto) {
        return flightService.addFlight(flightDto)
                .map(f-> ResponseEntity.status(HttpStatus.CREATED).body(f));
    }

    /**
     * Method that handles the HTTP Get request for retrieving all incoming flights for an operator.
     * @param operator
     * The name of the operator
     * @return
     * All incoming flights of the operator
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<Flight> getIncomingFlightsOfOperator(@RequestParam String operator){
        return flightService.getIncomingFlightsOfOperator(operator).log();
    }

    /**
     * Method that handles the HTTP requests for searching a flight using specific details like
     * departure/arriving city and departure/return date.
     * @param leaving
     * The city from which the flight takes off.
     * @param destination
     * The city in which the flight will arrive.
     * @param departureDate
     * The date when the flight departures.
     * @param returnDate
     * The date when the user want to return.
     * @return
     * A mono of SearchFlightResponseDto, which contains the closest flights for leaving and arriving that
     * match the given detail.
     */
    @GetMapping("/{leaving}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<SearchFlightResponseDto> searchFlight(@PathVariable(name = "leaving") String leaving,
                                                     @RequestParam(name = "destination", required = false) String destination,
                                                     @RequestParam(name = "departureDate", required = false) LocalDate departureDate,
                                                     @RequestParam(name = "returnDate", required = false) LocalDate returnDate,
                                                     @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNum,
                                                     @RequestParam(name = "pageSize", defaultValue = "100", required = false) int pageSize){
        return flightService.searchFlight(leaving, destination, departureDate, returnDate, pageNum, pageSize);
    }


}
