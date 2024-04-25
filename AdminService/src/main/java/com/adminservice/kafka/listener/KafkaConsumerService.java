package com.adminservice.kafka.listener;

import avro.BookingAdmin;
import avro.CheckResponse;
import com.adminservice.kafka.producer.KafkaProducerService;
import com.adminservice.service.FlightService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

    private final FlightService flightService;
    private final KafkaProducerService kafkaProducerService;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumerService(FlightService flightService, KafkaProducerService kafkaProducerService) {
        this.flightService = flightService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "admin-check", groupId = "admin-group")
    public void listen(ConsumerRecord<String, BookingAdmin> record) {

        try {
            BookingAdmin bookingAdmin = record.value();
            logger.info("Received booking: {}", bookingAdmin);
            CheckResponse checkResponse = new CheckResponse();
            checkResponse.setBookingId(bookingAdmin.getBookingId());
            try {
                flightService.decrementSeatsAvailable(bookingAdmin);
                checkResponse.setStatus(true);
            }
            catch (Exception e){
                checkResponse.setStatus(false);
            }
            finally {
                kafkaProducerService.sendMessage("admin-check-status", checkResponse);
            }
        } catch(Exception e) {
            logger.error("Received booking: {}", e.getMessage());
        }
    }
}
