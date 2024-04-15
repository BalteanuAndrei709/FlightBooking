package com.example.BookingServiceUpdated.repository;

import com.example.BookingServiceUpdated.model.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {
    Mono<Booking> findById(String id);
}
