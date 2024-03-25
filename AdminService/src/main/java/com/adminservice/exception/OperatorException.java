package com.adminservice.exception;

public class OperatorException extends RuntimeException{

    public OperatorException(String message){
        super(message);
    }

    public OperatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
