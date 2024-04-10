package com.adminservice.service;

import com.adminservice.dto.RegisterFlightDTO;
import com.adminservice.exception.InvalidParameterException;
import com.adminservice.model.Flight;
import com.adminservice.model.Operator;
import com.adminservice.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);
    private final OperatorService operatorService;

    @Autowired
    public FlightService(FlightRepository flightRepository, OperatorService operatorService) {
        this.flightRepository = flightRepository;
        this.operatorService = operatorService;
    }

    /**
     * Method that will retrieve all registered flight from the database.
     * @return
     * All registered flights.
     */
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    /**
     * Method that will retrieve a flight from the database based on its id.
     * Method also check if there is any flight registered with the received id as parameter.
     * @param id
     * id of the flight for which we need to retrieve the details.
     * @return
     * The Flight which has the id equal with the received parameter.
     */
    public Flight getFlight(Integer id) {
        var flight = flightRepository.findById(id);
        if (flight.isEmpty()) {
            throw new InvalidParameterException("No flight found with id " + id);
        }
        return flight.get();
    }

    /**
     * Method that will register a new flight with the received information from DTO.
     * The method also ensures that the name of the operator actually maps to a registered
     * operator.
     * @param registerFlightDTO
     * The details of the flight which the app needs to register.
     * @param operatorName
     * The name of the operator for which the app registers the flight.
     * @return
     * The newly created Flight entity.
     */
    public Flight registerFlight(RegisterFlightDTO registerFlightDTO,
                               String operatorName) {
        Operator operator = operatorService.findByName(operatorName);
        Flight flight = new Flight(
                operator,
                registerFlightDTO.getLeaving(),
                registerFlightDTO.getDestination(),
                registerFlightDTO.getNumberSeatsTotal(),
                registerFlightDTO.getDateOfDeparture(),
                registerFlightDTO.getTimeOfDeparture()
        );
        flight = flightRepository.save(flight);
        return flight;
    }

    /**
     * Method that will update the fields of the flight which has the id equal with the received
     * parameter.
     * @param registerFlightDTO
     * The new fields for the flight.
     * @param id
     * The id of the flight on which the method updates the fields.
     * @return
     * A Flight object, with the updated fields.
     */
    public Flight updateFlight(RegisterFlightDTO registerFlightDTO, Integer id) {
        var flightOptional = flightRepository.findById(id);
        if (flightOptional.isEmpty()) {
            throw new InvalidParameterException("No flight found with id " + id);
        }
        var flight = flightOptional.get();
        flight.setDestination(registerFlightDTO.getDestination());
        flight.setLeaving(registerFlightDTO.getLeaving());
        flight.setNumberSeatsTotal(registerFlightDTO.getNumberSeatsTotal());
        flight.setNumberSeatsAvailable(registerFlightDTO.getNumberSeatsTotal());
        flight.setDateOfDeparture(registerFlightDTO.getDateOfDeparture());
        flight.setTimeOfDeparture(registerFlightDTO.getTimeOfDeparture());
        return flightRepository.save(flight);
    }

    /**
     * Method which will delete a flight based on its id. Method also checks if there is
     * any flight registered with the given id.
     * @param id
     * id of the flight which needs to be deleted.
     * @return
     * The status of deletion.
     */
    public boolean deleteFlight(Integer id) {
        var flightOptional = flightRepository.findById(id);
        if (flightOptional.isEmpty()) {
            throw new InvalidParameterException("No flight found with id " + id);
        }
        var flight = flightOptional.get();
        var operator = flight.getOperator();
        operator.getAllFlights().remove(flight);
        operatorService.saveOperator(operator);
        flightRepository.delete(flight);
        return !flightRepository.existsById(id);
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
