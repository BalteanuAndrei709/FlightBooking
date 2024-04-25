package com.example.BookingServiceUpdated.kafka.listener;

import avro.CheckResponse;
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
    private final BookingService bookingService;

    public KafkaConsumerService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @KafkaListener(topics = "admin-check-status", groupId = "admin-group")
    public void listen(ConsumerRecord<String, CheckResponse> record) {
        try {
            CheckResponse checkResponse = record.value();
            if(!checkResponse.getStatus()){
                bookingService.sendNotification(checkResponse.getBookingId(), "Error at reserving seats.", true);
                return;
            }
            bookingService.sendForPaymentCheck(checkResponse.getBookingId());

        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "payment-check-status", groupId = "admin-group-payments")
    public void listenPayments(ConsumerRecord<String, CheckResponse> record) {
        logger.info("Received Kafka message for payments: Key - {}, Value - {}", record.key(), record.value());
        try {
            CheckResponse checkResponse = record.value();
            if (!checkResponse.getStatus()) {
                bookingService.sendNotification(checkResponse.getBookingId(), "Error at payment.", true);
                // to send to Admin Service to cancel the booking
                return;
            }
            bookingService.sendNotification(checkResponse.getBookingId(), "All good.", false);
        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }
}
