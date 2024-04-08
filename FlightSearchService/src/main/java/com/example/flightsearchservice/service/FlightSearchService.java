package com.example.flightsearchservice.service;

import com.example.flightsearchservice.dto.SearchFlightResponseDto;
import com.example.flightsearchservice.proxy.OperatorProxy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.LocalDate;

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
        return operatorProxy.getAll(leaving, optionalDestination, optionalDepartureDate, optionalReturnDate, pageNumber, pageSize);
    }
}
