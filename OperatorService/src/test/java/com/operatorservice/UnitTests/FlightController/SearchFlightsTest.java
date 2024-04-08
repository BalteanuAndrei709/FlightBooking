package com.operatorservice.UnitTests.FlightController;

import com.operatorservice.controller.FlightController;
import com.operatorservice.dto.SearchFlightResponseDto;
import com.operatorservice.mapper.FlightMapper;
import com.operatorservice.model.Flight;
import com.operatorservice.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FlightController.class)
@Import(FlightMapper.class)
public class SearchFlightsTest {

    @MockBean
    private FlightService flightService;
    @Autowired
    private WebTestClient webClient;
    String url = "/api/v1.0/flights";

    @Test
    void testSearchFlightAllParams(){

        String leaving = "Iasi";
        String destination = "London";
        LocalDate departureDate = LocalDate.parse("2024-05-13");
        LocalDate returnDate = LocalDate.parse("2024-05-15");

        Flight leavingFlight1 = new Flight("660be61e202736b8ad10b1e4", "WizzAir", departureDate,
                "02:49:12", "Krakow", "London",  7.57f);
        List<Flight> leavingFlights = List.of(leavingFlight1);

        Flight returningFlight = new Flight("660be689202736b8ad13c93d", "WizzAir", returnDate,
                "00:54:50", "London", "Krakow",  2.64f);
        List<Flight> returningFlights =  List.of(returningFlight);

        Mono<SearchFlightResponseDto> response = Mono.just(new SearchFlightResponseDto(leavingFlights, returningFlights));

        Mockito.when(flightService.searchFlight(leaving, destination, departureDate, returnDate, 0, 1)).thenReturn(response);

        webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(url + "/" + leaving)
                        .queryParam("destination", destination)
                        .queryParam("departureDate", departureDate)
                        .queryParam("returnDate", returnDate)
                        .queryParam("pageNumber", 0)
                        .queryParam("pageSize", 1)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(SearchFlightResponseDto.class)
                .consumeWith(result -> {
                    var responseBody = result.getResponseBody();

                    assert responseBody != null;
                    assert responseBody.getReturningFlight() != null;
                    assert responseBody.getLeavingFlight() != null;

                    assertEquals(responseBody.getLeavingFlight(), leavingFlights);
                    assertEquals(responseBody.getReturningFlight(), returningFlights);
                });
        }
}
