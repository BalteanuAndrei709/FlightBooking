package com.example.BookingServiceUpdated.dto;

import com.example.BookingServiceUpdated.model.BookingStatus;

public class BookingPaymentDTO {
    private String id;
    private Double price;

    public BookingPaymentDTO(){}

    public BookingPaymentDTO(String id, Double price) {
        this.id = id;
        this.price = price;
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
}
