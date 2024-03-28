package com.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @JsonBackReference
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

    public Flight(Integer id, Operator operator, String leaving, String destination,
                  Integer numberSeatsTotal, Integer numberSeatsAvailable, LocalDate dateOfDeparture, LocalTime timeOfDeparture) {
        this.id = id;
        this.operator = operator;
        this.leaving = leaving;
        this.destination = destination;
        this.numberSeatsTotal = numberSeatsTotal;
        this.numberSeatsAvailable = numberSeatsAvailable;
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
    }
}
