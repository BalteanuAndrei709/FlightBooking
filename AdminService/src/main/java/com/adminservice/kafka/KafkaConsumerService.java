//package com.adminservice.kafka;
//import com.adminservice.dto.BookingDTO;
//import com.adminservice.service.FlightService;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//public class KafkaConsumerService {
//
//    @Autowired
//    private FlightService flightService;
//    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//
//
//    @KafkaListener(topics = "bookings", groupId = "admin-group")
//    public void listen(ConsumerRecord<String, String> record) {
//
//        try {
//            BookingDTO bookingDTO = objectMapper.readValue(record.value(), BookingDTO.class);
//            logger.info("Received booking: {}", bookingDTO);
//
//            flightService.decrementSeatsAvailable(bookingDTO.getFlightId(), bookingDTO.getNumberOfSeats());
//        } catch(Exception e) {
//            logger.error("Received booking: {}", e.getMessage());
//        }
//
//    }
//}
