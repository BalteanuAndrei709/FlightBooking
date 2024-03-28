package com.adminservice.mapper;

import com.adminservice.dto.FlightDTO;
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
            //flightDTO.setOperator(flight.getOperator());
            flightDTO.setLeaving(flight.getLeaving());
            flightDTO.setDestination(flight.getDestination());
            flightDTO.setNumberSeatsTotal(flight.getNumberSeatsTotal());
            flightDTO.setNumberSeatsAvailable(flight.getNumberSeatsAvailable());
            return flightDTO;
        }
    }

    public Flight toEntity(FlightDTO flightDTO) {
        if (flightDTO == null) {
            return null;
        } else {
            Flight flight = new Flight();
            flight.setId(flightDTO.getId());
            //flight.setOperator(flightDTO.getOperator());
            flight.setLeaving(flightDTO.getLeaving());
            flight.setDestination(flightDTO.getDestination());
            flight.setNumberSeatsTotal(flightDTO.getNumberSeatsTotal());
            flight.setNumberSeatsAvailable(flightDTO.getNumberSeatsAvailable());
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

    public List<Flight> dtosToEntities(List<FlightDTO> dtoList) {
        if (dtoList.isEmpty())
            return Collections.emptyList();

        List<Flight> flightList = new ArrayList<>();
        for (FlightDTO dto : dtoList) {
            flightList.add(toEntity(dto));
        }
        return flightList;
    }

}
