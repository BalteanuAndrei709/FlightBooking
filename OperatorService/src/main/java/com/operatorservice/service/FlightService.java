package com.operatorservice.service;

import com.operatorservice.model.Flight;
import com.operatorservice.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final SimpleDateFormat formatter;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
        this.formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    }

    /**
     * Method that contains the business logic for flights.
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
    public List<Flight> findFlights(String leaving, String destination, String departureDate, String returnDate) throws ParseException {
        return flightRepository.findAllByDepartureDateAfter(formatter.parse(departureDate));
        // search return flight to match returnDate
    }

}
