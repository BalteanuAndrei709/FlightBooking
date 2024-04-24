package com.payment.paymentservice.dto;

public class BookingPaymentDTO {
    private String bookingId;
    private Double price;
    private String iban;

    public BookingPaymentDTO() {
    }

    public BookingPaymentDTO(String bookingId, Double price, String iban) {
        this.bookingId = bookingId;
        this.price = price;
        this.iban = iban;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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
