package com.adminservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity class for an operator.
 */
@Entity
@Table(name = "operator", uniqueConstraints = {
        @UniqueConstraint(name = "unique_name", columnNames = "name"),
        @UniqueConstraint(name = "unique_iban", columnNames = "iban"),
        @UniqueConstraint(name = "unique_api_search", columnNames = "api_search")
})
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "operator", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<Flight> allFlights;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String iban;
    @Column(nullable = false, name = "api_search")
    private String apiSearch;

    public Operator() {
    }

    public Operator(Integer id, List<Flight> allFlights, String name, String iban, String apiSearch) {
        this.id = id;
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
