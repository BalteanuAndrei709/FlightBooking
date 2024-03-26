package com.adminservice.exception;

public class FlightException extends RuntimeException {

    public FlightException(String message) {
        super(message);
    }

    public FlightException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
