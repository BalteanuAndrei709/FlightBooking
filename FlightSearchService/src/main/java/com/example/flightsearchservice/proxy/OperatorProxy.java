package com.example.flightsearchservice.proxy;

import com.example.flightsearchservice.dto.OperatorDTO;
import com.example.flightsearchservice.dto.SearchFlightResponseDto;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

import java.util.List;

@Component
public class OperatorProxy {

    public Flux<SearchFlightResponseDto> getAll(List<OperatorDTO> operators, String uri) {
        return Flux.fromIterable(operators)
                .flatMap(operator -> fluxBuilder(getWebClient(operator.getApiSearch()), uri))
                .subscribeOn(Schedulers.parallel());
    }

    private Flux<SearchFlightResponseDto> fluxBuilder(WebClient webClient, String uri){
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(SearchFlightResponseDto.class)
                .subscribeOn(Schedulers.parallel())
                .onErrorResume(WebClientResponseException.class, this::handleClientException)
                .onBackpressureDrop();
    }

    private Flux<SearchFlightResponseDto> handleClientException(WebClientResponseException e) {
        return Flux.error(new RuntimeException("WebClient request failed: " + e.getRawStatusCode(), e));
    }

    private WebClient getWebClient(String baseUrl) {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // Increase max buffer size to 10 MB
                .build();

        HttpClient httpClient = HttpClient.create().wiretap(true); //requests and responses passing through the client will be logged
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient
                .builder()
                .clientConnector(connector)
                .exchangeStrategies(strategies)
                .baseUrl(baseUrl)
                .build();
    }
}
