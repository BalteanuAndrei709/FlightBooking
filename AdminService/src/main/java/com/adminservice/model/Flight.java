package com.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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
}
