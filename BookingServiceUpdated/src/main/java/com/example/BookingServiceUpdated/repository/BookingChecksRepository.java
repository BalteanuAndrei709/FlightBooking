package com.example.BookingServiceUpdated.repository;

import com.example.BookingServiceUpdated.model.BookingChecks;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingChecksRepository extends ReactiveMongoRepository<BookingChecks, String> {
}
