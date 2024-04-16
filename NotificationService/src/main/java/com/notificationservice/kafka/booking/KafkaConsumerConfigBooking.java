package com.notificationservice.kafka.booking;

import com.notificationservice.dto.BookingDTO;
import com.notificationservice.dto.NotificationDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfigBooking {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public ConsumerFactory<String, BookingDTO> consumerFactoryBooking() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        System.out.println(bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                // Pass the Notification.class to the JsonDeserializer constructor
                new JsonDeserializer<>(BookingDTO.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookingDTO> concurrentKafkaListenerContainerFactoryBooking() {
        ConcurrentKafkaListenerContainerFactory<String, BookingDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryBooking());
        //factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}

