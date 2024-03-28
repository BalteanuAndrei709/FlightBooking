package com.adminservice.dto;

import com.adminservice.model.Operator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class FlightDTO {
    private Integer id;
    private String operatorName;
    private String leaving;
    private String destination;
    private Integer numberSeatsTotal;
    private Integer numberSeatsAvailable;
    private LocalDate dateOfDeparture;
    private LocalTime timeOfDeparture;

    public FlightDTO() {
    }

    public FlightDTO(Integer id, Operator operator, String leaving, String destination,
                     Integer numberSeatsTotal, Integer numberSeatsAvailable) {
        this.id = id;
        // this.operator = operator;
        this.leaving = leaving;
        this.destination = destination;
        this.numberSeatsTotal = numberSeatsTotal;
        this.numberSeatsAvailable = numberSeatsAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getLeaving() {
        return leaving;
    }

    public void setLeaving(String leaving) {
        this.leaving = leaving;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getNumberSeatsTotal() {
        return numberSeatsTotal;
    }

    public void setNumberSeatsTotal(Integer numberSeatsTotal) {
        this.numberSeatsTotal = numberSeatsTotal;
    }

    public Integer getNumberSeatsAvailable() {
        return numberSeatsAvailable;
    }

    public void setNumberSeatsAvailable(Integer numberSeatsAvailable) {
        this.numberSeatsAvailable = numberSeatsAvailable;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDTO flightDTO = (FlightDTO) o;
        return Objects.equals(id, flightDTO.id) && Objects.equals(operatorName, flightDTO.operatorName) && Objects.equals(leaving, flightDTO.leaving) && Objects.equals(destination, flightDTO.destination) && Objects.equals(numberSeatsTotal, flightDTO.numberSeatsTotal) && Objects.equals(numberSeatsAvailable, flightDTO.numberSeatsAvailable) && Objects.equals(dateOfDeparture, flightDTO.dateOfDeparture) && Objects.equals(timeOfDeparture, flightDTO.timeOfDeparture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operatorName, leaving, destination, numberSeatsTotal, numberSeatsAvailable, dateOfDeparture, timeOfDeparture);
    }
}
