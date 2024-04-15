package com.operatorservice.model;
import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(value = "flight")
public class Flight {

    @Id
    private String id;
    private String operator;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Column(name = "departure_time")
    private String departureTime;
    private String leaving;
    private String destination;
    private Float price;

    public Flight(String id, String operator, LocalDate departureDate, String departureTime, String leaving, String destination, Float price) {
        this.id = id;
        this.operator = operator;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.leaving = leaving;
        this.destination = destination;
        this.price = price;
    }

    public Flight() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", operator='" + operator + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime='" + departureTime + '\'' +
                ", leaving='" + leaving + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                '}';
    }
}
