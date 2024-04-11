package com.example.BookingServiceUpdated.mapper;


import com.example.BookingServiceUpdated.dto.BookingDTO;
import com.example.BookingServiceUpdated.dto.BookingDTO;
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
    public Booking toEntity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setFlightId(bookingDTO.getFlightId());
        booking.setUserName(bookingDTO.getUserName());
        //booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setPrice(bookingDTO.getPrice());
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        //booking.setBookingStatus(BookingStatus.PENDING);

        return booking;
    }
}

