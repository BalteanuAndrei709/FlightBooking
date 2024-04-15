package com.example.flightsearchservice.dto;

public class OperatorDTO {

    private Integer id;
    private String name;
    private String iban;
    private String apiSearch;

    public OperatorDTO() {
    }

    public OperatorDTO(Integer id, String name, String iban, String apiSearch) {
        this.id = id;
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
