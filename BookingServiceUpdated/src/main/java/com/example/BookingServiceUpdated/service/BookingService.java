package com.example.BookingServiceUpdated.service;

import com.example.BookingServiceUpdated.dto.BookingDTO;
import com.example.BookingServiceUpdated.kafka.producer.KafkaProducerService;
import com.example.BookingServiceUpdated.mapper.BookingMapper;
import com.example.BookingServiceUpdated.mapper.BookingPaymentMapper;
import com.example.BookingServiceUpdated.model.Booking;
import com.example.BookingServiceUpdated.model.BookingStatus;
import com.example.BookingServiceUpdated.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingService {


    private BookingRepository bookingRepository;

    private BookingMapper bookingMapper;

    private BookingPaymentMapper bookingPaymentMapper;

    private FlightService flightService;

    private KafkaProducerService kafkaProducerService;
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, FlightService flightService, KafkaProducerService kafkaProducerService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.flightService = flightService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Transactional
    public Mono<BookingDTO> createBooking(BookingDTO bookingDTO) {
        return Mono.just(bookingDTO)
                .map(bookingMapper::toEntity)
                .doOnNext(booking -> booking.setBookingStatus(BookingStatus.PENDING))
                .flatMap(bookingRepository::save)
                .doOnNext(booking -> {

                    kafkaProducerService.sendMessage("bookings-status-payments", bookingPaymentMapper.toDTO(booking))
                })
                .map(bookingMapper::toDTO);
    }

    public Mono<BookingDTO> getBookingById(String id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDTO);
    }

    public Flux<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .map(bookingMapper::toDTO);
    }

    @Transactional
    public Mono<BookingDTO> updateBooking(String id, BookingDTO bookingDTO) {
        return bookingRepository.findById(id)
                .map(existingBooking -> updateExistingBooking(existingBooking, bookingDTO))
                .flatMap(bookingRepository::save)
                .map(bookingMapper::toDTO);
    }

    private Booking updateExistingBooking(Booking booking, BookingDTO bookingDTO) {
        booking.setFlightId(bookingDTO.getFlightId());
        booking.setUserName(bookingDTO.getUserName());
        booking.setPrice(bookingDTO.getPrice());
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        return booking;
    }

    @Transactional
    public Mono<Void> updateBookingStatus(String bookingId, BookingStatus status) {
        return bookingRepository.findById(bookingId)
                .doOnNext(booking -> {
                    logger.info("Found booking for update: {}", booking);
                    booking.setBookingStatus(status);
                })
                .flatMap(bookingRepository::save)
                .doOnSuccess(booking -> logger.info("Booking updated successfully: {}", booking))
                .then();
    }



    // Additional methods for booking management
}

