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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "operator", cascade = CascadeType.ALL)
    List<Flight> allFlights;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String iban;
    @Column(unique = true, nullable = false, name = "api_search")
    private String apiSearch;

    public Operator() {
    }

    public Operator(List<Flight> allFlights, String name, String iban, String apiSearch) {
        this.allFlights = allFlights;
        this.name = name;
        this.iban = iban;
        this.apiSearch = apiSearch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Flight> getAllFlights() {
        return allFlights;
    }

    public void setAllFlights(List<Flight> allFlights) {
        this.allFlights = allFlights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getApiSearch() {
        return apiSearch;
    }

    public void setApiSearch(String apiSearch) {
        this.apiSearch = apiSearch;
    }
}
