package com.operatorservice.repository;

import com.operatorservice.model.Flight;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, ObjectId> {

    List<Flight> findAllByLeavingAndArrivingAndDepartureDateAfter(String leaving, String arriving, Date departureDate);

    List<Flight> findAllByLeavingAndArrivingAndDepartureDateBefore(String arriving, String leaving, Date returnDate);
}
