package com.example.BookingServiceUpdated.kafka.listener;

import com.example.BookingServiceUpdated.dto.BookingStatusDTO;
import com.example.BookingServiceUpdated.kafka.KafkaProducerService;
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

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    private KafkaProducerService kafkaProducerService;
    @Autowired
    private BookingService bookingService;



    @KafkaListener(topics = "bookings-status", groupId = "admin-bookings-group")
    public void listen(ConsumerRecord<String, String> record) {

        try {
            BookingStatusDTO bookingStatusDTO = objectMapper.readValue(record.value(), BookingStatusDTO.class);
            logger.info("Received booking status: {}", bookingStatusDTO);

            bookingService.updateBookingStatus(bookingStatusDTO.getId(), bookingStatusDTO.getBookingStatus());


        } catch(Exception e) {
            logger.error("Received booking: {}", e.getMessage());
        }

    }
}
