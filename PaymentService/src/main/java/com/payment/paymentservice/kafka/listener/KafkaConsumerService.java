package com.payment.paymentservice.kafka.listener;


import avro.BookingPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.paymentservice.service.PayPalService;
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
    private PayPalService payPalService;

    @KafkaListener(topics = "payment-check", groupId = "admin-group-payments")
    public void listen(ConsumerRecord<String, BookingPayment> record) {

        try {
            BookingPayment bookingPayment = record.value();
            logger.info("Received booking: {}", bookingPayment);
            payPalService.createPayment(bookingPayment).subscribe();

        } catch(Exception e) {
            logger.error("Received booking: {}", e.getMessage());
        }

    }
}
