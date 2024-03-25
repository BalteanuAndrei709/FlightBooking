package com.adminservice.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Entity class for an operator.
 */
@Entity
@Table(name = "operator")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "operator")
    List<Flight> allFlights;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String iban;
    @Column(unique = true, nullable = false, name = "api_search")
    private String apiSearch;
}
