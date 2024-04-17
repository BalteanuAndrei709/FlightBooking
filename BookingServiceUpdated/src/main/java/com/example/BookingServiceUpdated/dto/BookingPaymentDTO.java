package com.example.BookingServiceUpdated.dto;

import com.example.BookingServiceUpdated.model.BookingStatus;

public class BookingPaymentDTO {
    private String id;
    private Double price;
    private String iban;

    public BookingPaymentDTO() {
    }

    public BookingPaymentDTO(String id, Double price, String iban) {
        this.id = id;
        this.price = price;
        this.iban = iban;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
