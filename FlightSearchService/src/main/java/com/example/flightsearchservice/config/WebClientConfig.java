package com.example.flightsearchservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.*;

import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${operator.service.base.url1}")
    private String baseUrlWizzAir;

    @Value("${operator.service.base.url2}")
    private String baseUrlLufthansa;

    @Bean
    public WebClient webClientWizzAir(){

        return getWebClient(baseUrlWizzAir);
    }

    @Bean
    public WebClient webClientLufthansa(){

        return getWebClient(baseUrlLufthansa);
    }

    private WebClient getWebClient(String baseUrl) {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // Increase max buffer size to 10 MB
                .build();

        HttpClient httpClient = HttpClient.create().wiretap(true);
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient
                .builder()
                .clientConnector(connector)
                .exchangeStrategies(strategies)
                .baseUrl(baseUrl)
                .build();
    }


}
