package com.searchflight.dto;

import org.bson.types.ObjectId;

import java.util.Date;

public class FlightDto {
    private ObjectId id;
    private String operator;
    private String leaving;
    private String destination;
    private Date departureTime;
    private Date arrivalTime;
    private Date departureDate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
    public void setDestination(String arriving) {
        this.destination = arriving;
    }
    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
