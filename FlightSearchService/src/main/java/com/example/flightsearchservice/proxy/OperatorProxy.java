package com.example.flightsearchservice.proxy;

import com.example.flightsearchservice.dto.SearchFlightResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.time.LocalDate;
import java.util.Collections;

@Component
public class OperatorProxy {

    private final WebClient webClientWizzAir;
    private final WebClient webClientLufthansa;

    public OperatorProxy(WebClient webClientWizzAir, WebClient webClientLufthansa) {
        this.webClientWizzAir = webClientWizzAir;
        this.webClientLufthansa = webClientLufthansa;
    }

    public Flux<SearchFlightResponseDto> getAll(String leaving,
                                                String optionalDestination,
                                                LocalDate optionalDepartureDate,
                                                LocalDate optionalReturnDate) {

        String uri = uriBuilder(leaving, optionalDestination, optionalDepartureDate, optionalReturnDate);

        Flux<SearchFlightResponseDto> fluxLufthansa = fluxBuilder(webClientLufthansa, uri);

        Flux<SearchFlightResponseDto> fluxWizzAir = fluxBuilder(webClientWizzAir, uri);

        return Flux.zip(fluxLufthansa, fluxWizzAir)
                .flatMap(tuple -> {
                    Flux<SearchFlightResponseDto> flux1 = Flux.fromIterable(Collections.singletonList(tuple.getT1()));
                    Flux<SearchFlightResponseDto> flux2 = Flux.fromIterable(Collections.singletonList(tuple.getT2()));
                    return flux1.concatWith(flux2);
                })
                .log();
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

    private String uriBuilder(String leaving,
                              String optionalDestination,
                              LocalDate optionalDepartureDate,
                              LocalDate optionalReturnDate){
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/" + leaving);

        if (!StringUtils.isEmpty(optionalDestination)) {
            uriBuilder.queryParam("destination", optionalDestination);
        }
        if (optionalDepartureDate != null) {
            uriBuilder.queryParam("departureDate", optionalDepartureDate);
        }
        if (optionalReturnDate != null) {
            uriBuilder.queryParam("returnDate", optionalReturnDate);
        }

        return uriBuilder.toUriString();
    }

    private Flux<SearchFlightResponseDto> handleClientException(WebClientResponseException e) {
        return Flux.error(new RuntimeException("WebClient request failed: " + e.getRawStatusCode(), e));
    }

}
