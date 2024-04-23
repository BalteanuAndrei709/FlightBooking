package com.example.BookingServiceUpdated.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Entity class for a flight.
 */
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id", name = "operator_id")
    private Operator operator;
    @Column(nullable = false)
    private String leaving;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private Integer numberSeatsTotal;
    @Column(nullable = false)
    private Integer numberSeatsAvailable;
    @Column(nullable = false)
    private LocalDate dateOfDeparture;
    @Column(nullable = false)
    private LocalTime timeOfDeparture;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
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

    public Flight() {
    }

    public Flight(Operator operator, String leaving, String destination,
                  Integer numberSeatsTotal, LocalDate dateOfDeparture, LocalTime timeOfDeparture) {
        this.operator = operator;
        this.leaving = leaving;
        this.destination = destination;
        this.numberSeatsTotal = numberSeatsTotal;
        this.numberSeatsAvailable = numberSeatsTotal;
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(operator, flight.operator) && Objects.equals(leaving, flight.leaving) && Objects.equals(destination, flight.destination) && Objects.equals(numberSeatsTotal, flight.numberSeatsTotal) && Objects.equals(numberSeatsAvailable, flight.numberSeatsAvailable) && Objects.equals(dateOfDeparture, flight.dateOfDeparture) && Objects.equals(timeOfDeparture, flight.timeOfDeparture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operator, leaving, destination, numberSeatsTotal, numberSeatsAvailable, dateOfDeparture, timeOfDeparture);
    }
}
