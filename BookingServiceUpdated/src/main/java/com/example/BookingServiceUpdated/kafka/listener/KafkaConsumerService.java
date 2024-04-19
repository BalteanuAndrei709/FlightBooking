package com.example.BookingServiceUpdated.kafka.listener;

import com.example.BookingServiceUpdated.dto.CheckStatusDTO;
import com.example.BookingServiceUpdated.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BookingService bookingService;

    public KafkaConsumerService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @KafkaListener(topics = "admin-check-status", groupId = "admin-bookings-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            logger.info("Received Kafka message: Key - {}, Value - {}", record.key(), record.value());
            CheckStatusDTO adminStatusDTO = objectMapper.readValue(record.value(), CheckStatusDTO.class);
            if(!adminStatusDTO.getStatus()){
                bookingService.sendNotification(adminStatusDTO.getBookingId(), "Error at reserving seats.", true);
                return;
            }
            bookingService.sendForPaymentCheck(adminStatusDTO.getBookingId());

        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "payment-check-status", groupId = "admin-group-payments")
    public void listenPayments(ConsumerRecord<String, String> record) {
        logger.info("Received Kafka message for payments: Key - {}, Value - {}", record.key(), record.value());
        try {
            CheckStatusDTO adminStatusDTO = objectMapper.readValue(record.value(), CheckStatusDTO.class);
            if (!adminStatusDTO.getStatus()) {
                bookingService.sendNotification(adminStatusDTO.getBookingId(), "Error at payment.", true);
                return;
            }
            bookingService.sendNotification(adminStatusDTO.getBookingId(), "All good.", false);
        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }
}
