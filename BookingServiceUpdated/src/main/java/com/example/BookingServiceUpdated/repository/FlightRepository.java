package com.example.BookingServiceUpdated.repository;

import com.example.BookingServiceUpdated.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
