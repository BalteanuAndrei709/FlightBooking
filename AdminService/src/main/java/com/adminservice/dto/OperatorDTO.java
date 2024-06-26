package com.adminservice.dto;

import java.util.List;

public class OperatorDTO {

    private Integer id;
    List<FlightDTO> allFlights;
    private String name;
    private String iban;
    private String apiSearch;

    public OperatorDTO() {
    }

    public OperatorDTO(Integer id, List<FlightDTO> allFlights, String name, String iban, String apiSearch) {
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

    public List<FlightDTO> getAllFlights() {
        return allFlights;
    }

    public void setAllFlights(List<FlightDTO> allFlights) {
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
