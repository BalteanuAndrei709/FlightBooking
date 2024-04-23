package com.payment.paymentservice.mock;

public class BookingMock {

    private Double amount;
    private String iban;
    private String bookingId;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public BookingMock() {
    }

    public BookingMock(Double amount, String iban, String bookingId) {
        this.amount = amount;
        this.iban = iban;
        this.bookingId = bookingId;
    }
}
