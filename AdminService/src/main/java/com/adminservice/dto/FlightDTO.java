package com.adminservice.dto;

import com.adminservice.model.Operator;
public class FlightDTO {
    private Integer id;
    private OperatorDTO operatorDTO;
    private String leaving;
    private String destination;
    private Integer numberSeatsTotal;
    private Integer numberSeatsAvailable;

    public FlightDTO(){}

    public FlightDTO(Integer id, OperatorDTO operatorDTO, String leaving, String destination,
                     Integer numberSeatsTotal, Integer numberSeatsAvailable) {
        this.id = id;
        this.operatorDTO = operatorDTO;
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

    public OperatorDTO getOperator() {
        return operatorDTO;
    }

    public void setOperator(OperatorDTO operatorDTO) {
        this.operatorDTO = operatorDTO;
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
}
