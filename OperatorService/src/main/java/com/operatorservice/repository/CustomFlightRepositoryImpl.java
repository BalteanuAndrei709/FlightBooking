package com.operatorservice.repository;

import com.operatorservice.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Repository
public class CustomFlightRepositoryImpl implements CustomFlightRepository{

    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomFlightRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Method that will search for the leaving flights.
     * It takes into consideration the fields are present or not in the request.
     * The only field that is mandatory is "leaving".
     * @param leaving
     * The city from which the flight takes off.
     * @param destination
     * The city in which the flight will arrive.
     * @param departureDate
     * The date when the flight departures.
     * @param returnDate
     * The date when the user want to return.
     * @return
     * The flights that match the given criteria.
     */
    @Override
    public Flux<Flight> findLeavingFlights(String operator, String leaving, String destination,
                                           LocalDate departureDate, LocalDate returnDate, PageRequest pageRequest) {
        Query query = new Query().with(pageRequest);
        query.addCriteria(Criteria.where("leaving").is(leaving));
        query.addCriteria(Criteria.where("operator").is(operator));

        if (destination != null) {
            query.addCriteria(Criteria.where("destination").is(destination));
        }
        if (departureDate != null) {
            LocalDateTime startOfDay = departureDate.atStartOfDay(ZoneOffset.UTC).toLocalDateTime(); // Start of day in UTC
            LocalDateTime endOfDay = departureDate.plusDays(1).atStartOfDay(ZoneOffset.UTC).minusSeconds(1).toLocalDateTime(); // Just before the next day starts in UTC
            query.addCriteria(Criteria.where("departureDate").gte(startOfDay).lt(endOfDay));
        }
        if (returnDate != null){
            LocalDateTime endOfDay = returnDate.plusDays(1).atStartOfDay(ZoneOffset.UTC).minusSeconds(1).toLocalDateTime(); // Just before the next day starts in UTC
            query.addCriteria(Criteria.where("departureDate").lt(endOfDay));
        }
        return mongoTemplate.find(query, Flight.class);
    }

    /**
     * Method that will search for the leaving flights.
     * It takes into consideration the fields are present or not in the request.
     * The only field that is mandatory is "leaving".
     * @param leaving
     * The city from which the flight takes off.
     * @param destination
     * The city in which the flight will arrive.
     * @param departureDate
     * The date when the flight departures.
     * @param returnDate
     * The date when the user want to return.
     * @return
     * The flights that match the given criteria.
     */
    @Override
    public Flux<Flight> findReturningFlights(String operator, String leaving, String destination,
                                             LocalDate departureDate, LocalDate returnDate, PageRequest pageRequest) {
        Query query = new Query().with(pageRequest);
        query.addCriteria(Criteria.where("leaving").is(leaving));
        query.addCriteria(Criteria.where("destination").is(destination));
        query.addCriteria(Criteria.where("operator").is(operator));

        if (returnDate != null){
            LocalDateTime startOfDay = returnDate.atStartOfDay(ZoneOffset.UTC).toLocalDateTime(); // Start of day in UTC
            LocalDateTime endOfDay = returnDate.plusDays(1).atStartOfDay(ZoneOffset.UTC).minusSeconds(1).toLocalDateTime(); // Just before the next day starts in UTC
            query.addCriteria(Criteria.where("departureDate").gte(startOfDay).lt(endOfDay));
        }
        if (departureDate != null){
            LocalDateTime endOfDay = departureDate.plusDays(1).atStartOfDay(ZoneOffset.UTC).minusSeconds(1).toLocalDateTime(); // Just before the next day starts in UTC
            query.addCriteria(Criteria.where("departureDate").gt(endOfDay));
        }
        return mongoTemplate.find(query, Flight.class);
    }
}
