package com.example.BookingServiceUpdated.mapper;


import com.example.BookingServiceUpdated.dto.BookingDTO;
import com.example.BookingServiceUpdated.dto.CompressedBookingDTO;
import com.example.BookingServiceUpdated.dto.ReserveSeatsDTO;
import com.example.BookingServiceUpdated.model.Booking;
import com.example.BookingServiceUpdated.model.BookingStatus;
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
        bookingDTO.setUserName(booking.getUserName());
        //bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setPrice(booking.getPrice());
        bookingDTO.setNumberOfSeats(booking.getNumberOfSeats());
        return bookingDTO;
    }

    // Convert a BookingDTO to a Booking entity
    public Booking toEntity(CompressedBookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setFlightId(bookingDTO.getFlightId());
        booking.setUserName(bookingDTO.getUserName());
        booking.setPrice(bookingDTO.getPrice());
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setBookingStatus(BookingStatus.PENDING);
        return booking;
    }

    /**
     * Maps an entity of Booking to an ReserveSeatsDTO.
     * @param booking
     * The entity object of type Booking.
     * @return
     * An ReserveSeatsDTO
     */
    public ReserveSeatsDTO entityToReserveSeatsDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        ReserveSeatsDTO seatsDTO = new ReserveSeatsDTO();
        seatsDTO.setBookingId(booking.getId());
        seatsDTO.setNumberOfSeats(booking.getNumberOfSeats());
        seatsDTO.setFlightId(booking.getFlightId());
        return seatsDTO;
    }
}


