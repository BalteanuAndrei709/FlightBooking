package com.payment.paymentservice.kafka.producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "bookings-status-payments";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object msg) {
        kafkaTemplate.send(TOPIC, msg);
    }
}



