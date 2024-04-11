package com.adminservice.mapper;

import com.adminservice.dto.FlightDTO;
import com.adminservice.dto.CompressedFlightDTO;
import com.adminservice.model.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class FlightMapper {

    public FlightDTO toDTO(Flight flight) {
        if (flight == null)
            return null;
        else {
            FlightDTO flightDTO = new FlightDTO();
            flightDTO.setId(flight.getId());
            flightDTO.setOperatorName(flight.getOperator().getName());
            flightDTO.setLeaving(flight.getLeaving());
            flightDTO.setDestination(flight.getDestination());
            flightDTO.setNumberSeatsTotal(flight.getNumberSeatsTotal());
            flightDTO.setNumberSeatsAvailable(flight.getNumberSeatsAvailable());
            flightDTO.setDateOfDeparture(flight.getDateOfDeparture());
            flightDTO.setTimeOfDeparture(flight.getTimeOfDeparture());
            return flightDTO;
        }
    }

    /**
     * Method that will map a DTO for flight to an Flight entity.
     * @param flightDTO
     * The details of the flight.
     * @return
     * An entity of Flight, with the information received from the DTO.
     */
    public Flight toEntity(CompressedFlightDTO flightDTO) {
        if (flightDTO == null) {
            return null;
        } else {
            Flight flight = new Flight();
            flight.setLeaving(flightDTO.getLeaving());
            flight.setDestination(flightDTO.getDestination());
            flight.setNumberSeatsTotal(flightDTO.getNumberSeatsTotal());
            flight.setNumberSeatsAvailable(flightDTO.getNumberSeatsTotal());
            flight.setDateOfDeparture(flightDTO.getDateOfDeparture());
            flight.setTimeOfDeparture(flightDTO.getTimeOfDeparture());
            return flight;
        }
    }

    public List<FlightDTO> entitiesToDTOs(List<Flight> flightList) {
        if (flightList.isEmpty())
            return Collections.emptyList();

        List<FlightDTO> flightDTOS = new ArrayList<>();
        for (Flight flight : flightList) {
            flightDTOS.add(toDTO(flight));
        }
        return flightDTOS;
    }
}
