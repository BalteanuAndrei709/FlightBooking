package com.example.BookingServiceUpdated.mapper;


import com.example.BookingServiceUpdated.dto.BookingDTO;
import com.example.BookingServiceUpdated.dto.CompressedBookingDTO;
import com.example.BookingServiceUpdated.dto.BookingAdminDTO;
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
     * Maps an entity of Booking to an BookingAdminDTO.
     * @param booking
     * The entity object of type Booking.
     * @return
     * An BookingAdminDTO
     */
    public BookingAdminDTO entityToBookingAdminDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingAdminDTO seatsDTO = new BookingAdminDTO();
        seatsDTO.setBookingId(booking.getId());
        seatsDTO.setNumberOfSeats(booking.getNumberOfSeats());
        seatsDTO.setFlightId(booking.getFlightId());
        return seatsDTO;
    }
}


