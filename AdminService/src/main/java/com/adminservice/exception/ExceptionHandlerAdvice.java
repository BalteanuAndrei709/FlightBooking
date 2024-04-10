package com.adminservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The controller advice that will handle all the exception during the processing of the HTTP requests.
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    // Timestamp when the exception occurred.
    private static final String TIMESTAMP = "Timestamp";

    // Error message of the exception.
    private static final String ERROR_MESSAGE = "Errors";

    /**
     * Method that will handle all the exceptions in case of a wrong parameter.
     */
    @ExceptionHandler({InvalidParameterException.class})
    public ResponseEntity<Object> invalidParameterException(InvalidParameterException e){
        Map<String, Object> result = new HashMap<>();
        result.put(TIMESTAMP, LocalDateTime.now());
        result.put(ERROR_MESSAGE, e.getMessage());
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }
}
