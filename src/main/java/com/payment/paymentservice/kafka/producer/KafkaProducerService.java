package com.payment.paymentservice.kafka.producer;

import com.payment.paymentservice.dto.PaymentStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> template;

    @Value("${topic.name}")
    private String topicName;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void sendMessage(PaymentStatusDto statusDto) {
        template.send(topicName, statusDto);
    }

    public void sendMessage(PaymentStatusDto statusDto, String orderId) {
        template.send(topicName, orderId, statusDto);
    }
}
