package com.operatorservice.repository;

import com.operatorservice.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, Integer> {

    List<Flight> findAllByDepartureDateAfter(Date departureDate);
}
