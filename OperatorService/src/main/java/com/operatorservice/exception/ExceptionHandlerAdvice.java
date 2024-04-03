package com.operatorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    // Timestamp when the exception occurred.
    private static final String TIMESTAMP = "Timestamp";
    // Error message of the exception.
    private static final String ERROR_MESSAGE = "Errors";

    @ExceptionHandler({InvalidParameterException.class})
    public Mono<ResponseEntity<?>> handleCustomAsyncException(InvalidParameterException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put(TIMESTAMP, LocalDateTime.now());
        result.put(ERROR_MESSAGE, ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result));
    }

}
