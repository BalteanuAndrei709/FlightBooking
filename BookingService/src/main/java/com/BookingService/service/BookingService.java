package com.BookingService.service;

import com.BookingService.dto.BookingDTO;
import com.BookingService.mapper.BookingMapper;
import com.BookingService.model.Booking;
import com.BookingService.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {


    private BookingRepository bookingRepository;

    private BookingMapper bookingMapper;

    private FlightService flightService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, FlightService flightService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.flightService = flightService;
    }

    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);

        booking = bookingRepository.save(booking);
        bookingDTO.setId(booking.getId());

        flightService.decrementSeatsAvailable(bookingDTO.getFlightId(), bookingDTO.getNumberOfSeats());
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
        bookingToUpdate.setBookingDate(bookingDTO.getBookingDate());
        bookingToUpdate.setPrice(bookingDTO.getPrice());
        bookingToUpdate.setNumberOfSeats(bookingDTO.getNumberOfSeats());

        // Save the updated booking entity
        Booking updatedBooking = bookingRepository.save(bookingToUpdate);

        // Return the updated booking as a DTO
        return bookingMapper.toDTO(updatedBooking);
    }



    // Additional methods for booking management
}

