package com.adminservice.controller;

import com.adminservice.dto.FlightDTO;
import com.adminservice.dto.CompressedFlightDTO;
import com.adminservice.mapper.FlightMapper;
import com.adminservice.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/flight")
public class FlightController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    /**
     * Method that will handle the HTTP request related to retrieving all flights.
     * @return
     * All registered flights.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllFlights() {
        var allFlights = flightService.getAllFlights();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightMapper.entitiesToDTOs(allFlights));
    }

    /**
     * Method that will retrieve a flight based on its id.
     * @param id
     * The id of the flight which the method needs to retrieve.
     * @return
     * The details of the flight which has the id equal with the received path variable.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable(name = "id") Integer id) {
        var flight = flightService.getFlight(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightMapper.toDTO(flight));
    }

    /**
     * Method that will handle the HTTP request related to creating a new flight.
     * @param compressedFlightDTO
     * The information about the new flight which the method need to create.
     * @param operatorName
     * The name of the operator which performs the flight the app needs to register.
     * @return
     * A FlightDTO, which contains all the information about the newly created Flight.
     */
    @PostMapping("/{operatorName}")
    public ResponseEntity<?> addFlight(@RequestBody CompressedFlightDTO compressedFlightDTO,
                                            @PathVariable(name = "operatorName") String operatorName) {
        var flight = flightService.registerFlight(compressedFlightDTO, operatorName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightMapper.toDTO(flight));
    }

    /**
     * Method that will handle the HTTP request related to updating the details of a flight.
     * @param compressedFlightDTO
     * The new details of the flight.
     * @param id
     * The id of the flight which need to be updated.
     * @return
     * An FlightDto object, with the updated fields.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FlightDTO> updateFlight(@RequestBody CompressedFlightDTO compressedFlightDTO,
                                                @PathVariable(name = "id") Integer id) {
        var flight = flightService.updateFlight(compressedFlightDTO, id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightMapper.toDTO(flight));
    }

    /**
     * Method that will handle the HTTP request related to deletion of a flight.
     * @param id
     * The id of the flight which needs to be deleted.
     * @return
     * The status of deletion of th flight.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable(name = "id") Integer id) {
        var status = flightService.deleteFlight(id);
        return status ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Flight with id " + id + " successfully deleted.") :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error at deleting flight with id " + id + ".");
    }
}
