package com.searchflight.repository;

import com.searchflight.model.Flight;
import com.searchflight.model.Operator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer> {

    List<Flight> findAllByLeavingAndArriving(String leaving, String arriving);
}
