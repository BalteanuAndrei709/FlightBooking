package com.example.BookingServiceUpdated.controller;


import com.example.BookingServiceUpdated.dto.BookingDTO;
import com.example.BookingServiceUpdated.dto.CompressedBookingDTO;
import com.example.BookingServiceUpdated.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Handles the HTTP request for booking a flight.
     * @param bookingDTO
     * Contains information about both flight and the user which books it.
     */
    @PostMapping()
    public void addBooking(@RequestBody CompressedBookingDTO bookingDTO) {
         bookingService.createBooking(bookingDTO);
    }
}
