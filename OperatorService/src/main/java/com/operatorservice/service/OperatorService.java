package com.operatorservice.service;

import com.operatorservice.dto.AvailableFlightsDto;
import com.operatorservice.model.Flight;
import com.operatorservice.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
public class OperatorService {

    private final FlightRepository flightRepository;

    private final SimpleDateFormat formatter;

    public OperatorService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
        this.formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    }

    /**
     * Method that contains the business logic for flights.
     * @param leaving
     * The city from which the user wants to travel.
     * @param arriving
     * The city to which the user wants to travel.
     * @param departureDate
     * Departure date of the flight.
     * @param returnDate
     * Return date of the flight.
     * @return
     * List of available options that match the given criteria.
     */
    public AvailableFlightsDto findFlights(String leaving, String arriving, String departureDate, String returnDate) throws ParseException {

        List<Flight> flightsLeavingAfterDepartureDate = flightRepository.findAllByLeavingAndArrivingAndDepartureDateAfter(
                leaving, arriving, formatter.parse(departureDate));

        List<Flight> returnFlightsLeavingBeforeReturnDate = flightRepository.findAllByLeavingAndArrivingAndDepartureDateBefore(
                arriving, leaving, formatter.parse(returnDate));

        return new AvailableFlightsDto(flightsLeavingAfterDepartureDate, returnFlightsLeavingBeforeReturnDate);
    }
}
