package com.example.BookingServiceUpdated.service;

import com.example.BookingServiceUpdated.kafka.KafkaProducerService;
import com.example.BookingServiceUpdated.mapper.BookingMapper;
import com.example.BookingServiceUpdated.model.Booking;
import com.example.BookingServiceUpdated.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.BookingServiceUpdated.dto.BookingDTO;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {


    private BookingRepository bookingRepository;

    private BookingMapper bookingMapper;

    private FlightService flightService;

    private KafkaProducerService kafkaProducerService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, FlightService flightService, KafkaProducerService kafkaProducerService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.flightService = flightService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);

        booking = bookingRepository.save(booking);
        bookingDTO.setId(booking.getId());

        //flightService.decrementSeatsAvailable(bookingDTO.getFlightId(), bookingDTO.getNumberOfSeats());
        kafkaProducerService.sendMessage(bookingDTO);
        return bookingDTO;
    }

    public Optional<BookingDTO> getBookingById(Integer id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDTO);
    }

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }
    public BookingDTO updateBooking(Integer id, BookingDTO bookingDTO) {
        Booking bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        // Update the booking entity's fields with values from the bookingDTO
        bookingToUpdate.setFlightId(bookingDTO.getFlightId());
        bookingToUpdate.setUserName(bookingDTO.getUserName());
        //bookingToUpdate.setBookingDate(bookingDTO.getBookingDate());
        bookingToUpdate.setPrice(bookingDTO.getPrice());
        bookingToUpdate.setNumberOfSeats(bookingDTO.getNumberOfSeats());

        // Save the updated booking entity
        Booking updatedBooking = bookingRepository.save(bookingToUpdate);

        // Return the updated booking as a DTO
        return bookingMapper.toDTO(updatedBooking);
    }



    // Additional methods for booking management
}

