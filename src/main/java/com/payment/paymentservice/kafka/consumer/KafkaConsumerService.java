package com.payment.paymentservice.kafka.consumer;

import com.payment.paymentservice.dto.PaymentStatusDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {


    @KafkaListener(topics = "booking-information", groupId = "payment-status")
    public void listen(ConsumerRecord<String, PaymentStatusDto> consumerRecord) {
        System.out.println("Consumer received the following value: " + consumerRecord.value());
    }
}
