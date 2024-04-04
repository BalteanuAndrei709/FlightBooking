package com.example.flightsearchservice.handlers;

import com.example.flightsearchservice.dto.SearchFlightResponseDto;
import com.example.flightsearchservice.model.Flight;
import com.example.flightsearchservice.service.FlightSearchService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class FlightHandler {

    private final FlightSearchService flightSearchService;

    public FlightHandler(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    public Mono<ServerResponse> getAll(ServerRequest request){
        var leaving = request.pathVariable("leaving");
        String optionalDestination = request.queryParam("destination").orElse(null);
//        try{
//            LocalDate optionalDepartureDate = LocalDate.parse(request.queryParam("departureTime").orElse(null));
//            LocalDate optionalReturnDate = LocalDate.parse(request.queryParam("returnDate").orElse(null));
//
//            return ok()
//                    .contentType(MediaType.TEXT_EVENT_STREAM)
//                    .body(flightSearchService.getAll(leaving, optionalDestination,
//                            optionalDepartureDate, optionalReturnDate), SearchFlightResponseDto.class)
//                    .log();
//        } catch (NullPointerException e){
//            LocalDate optionalDepartureDate = null;
//            LocalDate optionalReturnDate = null;
//
//            return ok()
//                    .contentType(MediaType.TEXT_EVENT_STREAM)
//                    .body(flightSearchService.getAll(leaving, optionalDestination,
//                            optionalDepartureDate, optionalReturnDate), SearchFlightResponseDto.class)
//                    .log();
//        }

        try {
            LocalDate optionalDepartureDate = request.queryParam("departureDate").map(LocalDate::parse).orElse(null);
            LocalDate optionalReturnDate = request.queryParam("returnDate").map(LocalDate::parse).orElse(null);

            return ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(flightSearchService.getAll(leaving,
                            optionalDestination,
                            optionalDepartureDate,
                            optionalReturnDate),
                            SearchFlightResponseDto.class)
                    .log();
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return badRequest().body("Invalid date format", String.class);
        }

    }
}
