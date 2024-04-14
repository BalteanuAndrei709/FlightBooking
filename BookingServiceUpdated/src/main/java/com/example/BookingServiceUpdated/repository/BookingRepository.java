package com.example.BookingServiceUpdated.repository;

import com.example.BookingServiceUpdated.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    // MongoDB uses String ID by default. Adjust if your ID type differs.
}
