package com.example.BookingServiceUpdated.kafka.listener;

import com.example.BookingServiceUpdated.dto.BookingStatusDTO;
import com.example.BookingServiceUpdated.kafka.producer.KafkaProducerService;
import com.example.BookingServiceUpdated.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private BookingService bookingService;

    @KafkaListener(topics = "bookings-status", groupId = "admin-bookings-group")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Received Kafka message: Key - {}, Value - {}", record.key(), record.value());
        try {
            BookingStatusDTO bookingStatusDTO = objectMapper.readValue(record.value(), BookingStatusDTO.class);
            bookingService.updateBookingStatus(bookingStatusDTO.getId(), bookingStatusDTO.getBookingStatus()).subscribe(
                    success -> logger.info("Successfully updated booking status for ID: {}", bookingStatusDTO.getId()),
                    error -> logger.error("Failed to update booking status: {}", error.getMessage())
            );
        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "bookings-status-payments", groupId = "admin-group-payments")
    public void listenPayments(ConsumerRecord<String, String> record) {
        logger.info("Received Kafka message for payments: Key - {}, Value - {}", record.key(), record.value());
        try {
            // Process the payment message here
        } catch (Exception e) {
            logger.error("Error processing Kafka message for payments: {}", e.getMessage(), e);
        }
    }




}
