package com.adminservice.dto;

public class RegisterOperatorDto {

    private String name;
    private String iban;
    private String apiSearch;

    public RegisterOperatorDto() {
    }

    public RegisterOperatorDto(String name, String iban, String apiSearch) {
        this.name = name;
        this.iban = iban;
        this.apiSearch = apiSearch;
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
