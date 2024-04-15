package com.example.flightsearchservice.service;

import com.example.flightsearchservice.dto.OperatorDTO;
import com.example.flightsearchservice.dto.SearchFlightResponseDto;
import com.example.flightsearchservice.proxy.OperatorProxy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FlightSearchService {

    private final OperatorProxy operatorProxy;


    public FlightSearchService(OperatorProxy operatorProxy) {
        this.operatorProxy = operatorProxy;
    }

    public Flux<SearchFlightResponseDto> getAll(String leaving,
                                                String optionalDestination,
                                                LocalDate optionalDepartureDate,
                                                LocalDate optionalReturnDate,
                                                Integer pageNumber,
                                                Integer pageSize){
        String uri = uriBuilder(
                leaving, optionalDestination, optionalDepartureDate,
                optionalReturnDate, pageNumber, pageSize);
        List<OperatorDTO> operators = getAvailableOperators(leaving,optionalDestination);
        return operatorProxy.getAll(operators, uri);
    }

    private List<OperatorDTO> getAvailableOperators(String leaving, String destination) {
        String BASE_URL = "http://admin-service:8000/api/v1.0/operator/routes";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("leaving", leaving);
        if (destination != null && !destination.isEmpty()) {
            builder.queryParam("destination", destination);
        }
        String finalUrl = builder.toUriString();
        ResponseEntity<List<OperatorDTO>> response = restTemplate.exchange(
                finalUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    private String uriBuilder(String leaving, String optionalDestination, LocalDate optionalDepartureDate,
                              LocalDate optionalReturnDate, Integer pageNumber, Integer pageSize){
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
        if (pageNumber != null) {
            uriBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriBuilder.queryParam("pageSize", pageSize);
        }
        return uriBuilder.toUriString();
    }
}
