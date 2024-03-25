package com.adminservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
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
}
