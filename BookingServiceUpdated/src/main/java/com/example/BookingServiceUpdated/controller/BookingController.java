package com.example.BookingServiceUpdated.controller;


import com.example.BookingServiceUpdated.dto.BookingDTO;
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

    @PostMapping()
    public Mono<ResponseEntity<String>> addBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO)
                .map(createdBooking -> ResponseEntity.ok(
                        String.format("Congratulations! Flight booked for under name: %s", createdBooking.getUserName())))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BookingDTO>> getBookingById(@PathVariable String id) {
        return bookingService.getBookingById(id)
                .map(bookingDTO -> ResponseEntity.ok(bookingDTO))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public Mono<ResponseEntity<Flux<BookingDTO>>> getAllBookings() {
        Flux<BookingDTO> bookings = bookingService.getAllBookings();
        return Mono.just(ResponseEntity.ok(bookings));
    }




}
