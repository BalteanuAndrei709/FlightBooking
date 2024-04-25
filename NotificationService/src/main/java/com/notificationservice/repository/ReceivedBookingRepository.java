package com.notificationservice.repository;

import com.notificationservice.model.Booking;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivedBookingRepository extends ElasticsearchRepository<Booking, String> {
}
