package com.example.BookingServiceUpdated.mapper;

import avro.BookingAdmin;
import com.example.BookingServiceUpdated.dto.BookingDTO;
import com.example.BookingServiceUpdated.dto.CompressedBookingDTO;
import com.example.BookingServiceUpdated.model.Booking;
import com.example.BookingServiceUpdated.model.BookingStatus;
import com.example.BookingServiceUpdated.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    // Convert a Booking entity to a BookingDTO
    public BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setFlightId(booking.getFlightId());
        bookingDTO.setEmail(booking.getEmail());
        //bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setPrice(booking.getPrice());
        bookingDTO.setNumberOfSeats(booking.getNumberOfSeats());
        return bookingDTO;
    }

    // Convert a BookingDTO to a Booking entity
    public Booking toEntity(CompressedBookingDTO bookingDTO, Flight flight) {
        if (bookingDTO == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setFlightId(flight.getId());
        booking.setEmail(bookingDTO.getUserName());
        booking.setPrice(bookingDTO.getPrice());
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setBookingStatus(BookingStatus.PENDING);
        return booking;
    }

    /**
     * Maps an entity of Booking to an BookingAdmin.
     * @param booking
     * The entity object of type Booking.
     * @return
     * An BookingAdmin
     */
    public BookingAdmin entityToBookingAdmin(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingAdmin bookingAdmin = new BookingAdmin();
        bookingAdmin.setBookingId(booking.getId());
        bookingAdmin.setNumberOfSeats(booking.getNumberOfSeats());
        bookingAdmin.setFlightId(booking.getFlightId());
        return bookingAdmin;
    }
}


