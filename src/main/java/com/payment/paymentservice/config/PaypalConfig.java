package com.payment.paymentservice.config;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PaypalConfig {
    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "advanced-beetle-11713-eu2-kafka.upstash.io:9092");
        properties.setProperty("sasl.mechanism", "SCRAM-SHA-256");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"YWR2YW5jZWQtYmVldGxlLTExNzEzJJWiNPRnLrK7heJN2nqlYP6aBD1NtV1K_Lg\" password=\"ZDUyNGM4MTktNGIzZC00ODllLTk0ZWQtNTBiNjAyMTdiNTUx\";");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        return properties;
    }

}
