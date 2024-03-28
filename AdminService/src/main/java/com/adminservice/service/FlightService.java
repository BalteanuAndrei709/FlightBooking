package com.adminservice.service;

import com.adminservice.dto.FlightDTO;
import com.adminservice.dto.OperatorDTO;
import com.adminservice.exception.FlightException;
import com.adminservice.mapper.FlightMapper;
import com.adminservice.mapper.OperatorMapper;
import com.adminservice.model.Flight;
import com.adminservice.model.Operator;
import com.adminservice.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightMapper flightMapper;
    private final FlightRepository flightRepository;

    private final OperatorMapper operatorMapper;
    private final OperatorService operatorService;

    @Autowired
    public FlightService(FlightMapper flightMapper, FlightRepository flightRepository,
                         OperatorMapper operatorMapper, OperatorService operatorService) {

        this.flightMapper = flightMapper;
        this.flightRepository = flightRepository;
        this.operatorMapper = operatorMapper;
        this.operatorService = operatorService;
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
            flight.setOperator(operatorMapper.toEntity(operatorDTO.get()));
            flightRepository.save(flight);
        }
    }

    public boolean updateFlight(FlightDTO updatedFlightDto, Integer id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        boolean result = flightOptional.isPresent();
        if (result) {
            Operator currentOperator = flightOptional.get().getOperator();   // save operator to be set again after the update takes place
            FlightDTO existingFlight = flightMapper.toDTO(flightOptional.get());
            existingFlight.setDestination(updatedFlightDto.getDestination());
            existingFlight.setLeaving(updatedFlightDto.getLeaving());
            existingFlight.setNumberSeatsTotal(updatedFlightDto.getNumberSeatsTotal());
            existingFlight.setNumberSeatsAvailable(updatedFlightDto.getNumberSeatsAvailable());
            existingFlight.setDateOfDeparture(updatedFlightDto.getDateOfDeparture());
            existingFlight.setTimeOfDeparture(updatedFlightDto.getTimeOfDeparture());

            Flight flight = flightMapper.toEntity(existingFlight);
            flight.setOperator(currentOperator);
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

}
