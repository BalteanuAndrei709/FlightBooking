package com.example.flightsearchservice.config;

import com.example.flightsearchservice.exceptions.FlightsException;
import com.example.flightsearchservice.handlers.FlightHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(FlightHandler flightHandler){
        return route().GET("/getAll/{leaving}" , flightHandler::getAll)
                .onError(FlightsException.class, (ex, request) -> ServerResponse.badRequest().bodyValue("An unexpected error appeared, please try again!"))
                .build();
    }

}
