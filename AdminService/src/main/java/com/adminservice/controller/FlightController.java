package com.adminservice.controller;

import com.adminservice.dto.FlightDTO;
import com.adminservice.dto.OperatorDTO;
import com.adminservice.mapper.OperatorMapper;
import com.adminservice.model.Flight;
import com.adminservice.model.Operator;
import com.adminservice.service.FlightService;
import com.adminservice.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
  /*  private final OperatorService operatorService;
    private final OperatorMapper operatorMapper;*/

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Optional<FlightDTO> getFlightById(@PathVariable(name = "id")
                                             Integer id) {

        return flightService.getFlight(id);
    }

    @PostMapping("/add/{operatorName}")
    public ResponseEntity<String> addFlight(@RequestBody FlightDTO flightDTO,
                                            @PathVariable(name = "operatorName") String operatorName) {

        flightService.createFlight(flightDTO, operatorName);
        return new ResponseEntity<>("Flight was added succesfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateFlight(@RequestBody FlightDTO flightDTO,
                                                @PathVariable(name = "id") Integer id) {

        return new ResponseEntity<>(flightService.updateFlight(flightDTO, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteFlight(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(flightService.deleteFlight(id), HttpStatus.OK);
    }
}
