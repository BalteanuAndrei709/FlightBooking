package com.example.BookingServiceUpdated.dto;

public class NotificationMessageDTO {

    private String message;
    private Boolean error;
    private String email;

    public NotificationMessageDTO() {
    }

    public NotificationMessageDTO(String message, Boolean error, String email) {
        this.message = message;
        this.error = error;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
