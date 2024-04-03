package com.operatorservice.mapper;

import com.operatorservice.dto.FlightDto;
import com.operatorservice.model.Flight;
import org.springframework.stereotype.Component;

/**
 * Mapper class related flights of an operator.
 */
@Component
public class FlightMapper {

    /**
     * Method that maps from a FlightDto to the domain object.
     * @param flightDto
     * The Dto that contains all the information about the flight.
     * @return
     * The Flight domain object.
     */
    public Flight mapDtoToFlight(FlightDto flightDto) {

        Flight flight = new Flight();
        flight.setDestination(flightDto.getDestination());
        flight.setDepartureDate(flightDto.getDepartureDate());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setLeaving(flightDto.getLeaving());
        flight.setPrice(flightDto.getPrice());
        return flight;
    }
}
