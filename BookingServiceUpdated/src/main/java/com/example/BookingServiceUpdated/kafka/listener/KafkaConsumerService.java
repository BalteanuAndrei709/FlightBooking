package com.example.BookingServiceUpdated.kafka.listener;

import com.example.BookingServiceUpdated.dto.BookingAdminStatusDTO;
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
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private BookingService bookingService;

    @KafkaListener(topics = "admin-reserve-seats-status", groupId = "admin-bookings-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            logger.info("Received Kafka message: Key - {}, Value - {}", record.key(), record.value());
            BookingAdminStatusDTO adminStatusDTO = objectMapper.readValue(record.value(), BookingAdminStatusDTO.class);
            bookingService.updateBookingChecks(adminStatusDTO);
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
