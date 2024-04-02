package com.operatorservice.repository;

import com.operatorservice.model.Flight;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface FlightRepository extends ReactiveMongoRepository<Flight, String>, CustomFlightRepository {

    Mono<Boolean> existsByOperator(String operator);

    Flux<Flight> getFlightsByOperatorAndDepartureDateAfter(String operator, LocalDate date);
}
