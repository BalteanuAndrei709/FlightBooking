package com.example.BookingServiceUpdated.service;

import com.example.BookingServiceUpdated.model.Flight;
import com.example.BookingServiceUpdated.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightRepository flightRepository;

    @Transactional
    public void decrementSeatsAvailable(Integer flightId, int seatsToDecrement) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found with id: " + flightId));
        logger.info("Current available seats before decrement: {}", flight.getNumberOfSeatsAvailable());

        int currentSeats = flight.getNumberOfSeatsAvailable();
        if (currentSeats < seatsToDecrement) {
            throw new IllegalStateException("Not enough available seats to decrement");
        }

        flight.setNumberOfSeatsAvailable(currentSeats - seatsToDecrement);
        flightRepository.save(flight);
        logger.info("Current available seats after decrement: {}", flight.getNumberOfSeatsAvailable());

    }
}
