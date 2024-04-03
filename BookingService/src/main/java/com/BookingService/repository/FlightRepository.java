package com.BookingService.repository;

import com.BookingService.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    // You can add custom query methods here if needed
}

