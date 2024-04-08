package com.adminservice.service;

import com.adminservice.dto.FlightDTO;
import com.adminservice.dto.OperatorDTO;
import com.adminservice.exception.FlightException;
import com.adminservice.mapper.FlightMapper;
import com.adminservice.mapper.OperatorMapper;
import com.adminservice.model.Flight;
import com.adminservice.model.Operator;
import com.adminservice.repository.FlightRepository;
import com.adminservice.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightMapper flightMapper;
    private final FlightRepository flightRepository;

    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;
    private final OperatorService operatorService;

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    public FlightService(FlightMapper flightMapper, FlightRepository flightRepository,
                         OperatorMapper operatorMapper, OperatorService operatorService,
                         OperatorRepository operatorRepository) {

        this.flightMapper = flightMapper;
        this.flightRepository = flightRepository;
        this.operatorMapper = operatorMapper;
        this.operatorService = operatorService;
        this.operatorRepository = operatorRepository;
    }

    public Optional<FlightDTO> getFlight(Integer id) {
        if (flightRepository.findById(id).isPresent()) {
            return flightRepository.findById(id).map(flightMapper::toDTO);
        } else
            throw new FlightException("There is no flight available.");
    }

    public List<FlightDTO> getAllFlights() {
        return flightMapper.entitiesToDTOs(flightRepository.findAll());
    }

    public void createFlight(FlightDTO dto, String operatorName) {
        Optional<OperatorDTO> operatorDTO = operatorService.findByName(operatorName);
        if (operatorDTO.isPresent()) {
            Flight flight = flightMapper.toEntity(dto);
            var operator = operatorMapper.toEntity(operatorDTO.get());
            flight.setOperator(operator);
            operator.getAllFlights().add(flight);
            operatorRepository.save(operator);
        }
    }

    public boolean updateFlight(FlightDTO updatedFlightDto, Integer id) {
        var flightOptional = flightRepository.findById(id);

        if (flightOptional.isPresent()) {
            var flight = flightOptional.get();
            flight.setDestination(updatedFlightDto.getDestination());
            flight.setLeaving(updatedFlightDto.getLeaving());
            flight.setNumberSeatsTotal(updatedFlightDto.getNumberSeatsTotal());
            flight.setNumberSeatsAvailable(updatedFlightDto.getNumberSeatsAvailable());
            flight.setDateOfDeparture(updatedFlightDto.getDateOfDeparture());
            flight.setTimeOfDeparture(updatedFlightDto.getTimeOfDeparture());
            flightRepository.save(flight);
            return true;
        } else
            throw new FlightException("The flight with id " + id + " doesn't exist");
    }

    public boolean deleteFlight(Integer id) {
        boolean result = flightRepository.findById(id).isPresent();
        if (result) {
            flightRepository.deleteById(id);
            return true;
        } else
            throw new FlightException("Flight with id " + id + " was not found");
    }

    @Transactional
    public void decrementSeatsAvailable(Integer flightId, int seatsToDecrement) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found with id: " + flightId));
        logger.info("Current available seats before decrement: {}", flight.getNumberSeatsAvailable());

        int currentSeats = flight.getNumberSeatsAvailable();
        if (currentSeats < seatsToDecrement) {
            throw new IllegalStateException("Not enough available seats to decrement");
        }

        flight.setNumberSeatsAvailable(currentSeats - seatsToDecrement);
        flightRepository.save(flight);
        logger.info("Current available seats after decrement: {}", flight.getNumberSeatsAvailable());

    }


}
