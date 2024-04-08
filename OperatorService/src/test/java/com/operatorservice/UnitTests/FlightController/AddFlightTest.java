package com.operatorservice.UnitTests.FlightController;

import com.operatorservice.controller.FlightController;
import com.operatorservice.dto.FlightDto;
import com.operatorservice.mapper.FlightMapper;
import com.operatorservice.model.Flight;
import com.operatorservice.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FlightController.class)
@Import(FlightMapper.class)
public class AddFlightTest {

    @MockBean
    private FlightService flightService;
    @Autowired
    private FlightMapper flightMapper;
    @Autowired
    private WebTestClient webClient;

    private String operator = "WizzAir";
    String url = "/api/v1.0/flights";

    @Test
    void testAddFlight() {
        // Create a FlightDto object with test data
        FlightDto flightDto = new FlightDto();
        flightDto.setDepartureDate(LocalDate.parse("2024-04-01"));
        flightDto.setDestination("Bucharest");
        flightDto.setLeaving("London");
        flightDto.setPrice(299.99f);
        flightDto.setDepartureTime("10:30");

        Flight expectedFlight = flightMapper.mapDtoToFlight(flightDto);
        expectedFlight.setOperator(operator);
        expectedFlight.setId("mockId");

        // Mock the service layer to return the expected Flight object when addFlight is called
        Mockito.when(flightService.addFlight(isA(FlightDto.class))).thenReturn(Mono.just(expectedFlight));

        // Perform a POST request with the FlightDto object and assert the response
        webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(flightDto))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Flight.class)
                .consumeWith(result -> {
                    var existsFlight = result.getResponseBody();

                    assert Objects.requireNonNull(existsFlight).getId() != null;
                    assertEquals("mockId", existsFlight.getId());
                    assertEquals(operator, existsFlight.getOperator());
                    assertEquals(existsFlight.getDepartureTime(), flightDto.getDepartureTime());
                });
    }
}