package com.notificationservice.mapper;

import com.notificationservice.dto.BookingDTO;
import com.notificationservice.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    /**
     * Method that maps a BookingDTO to an entity of Booking.
     * @param bookingDTO
     * The DTO received from kafka.
     * @return
     * An entity of Booking.
     */
    public Booking fromDTO(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setFlightId(bookingDTO.getFlightId());
        booking.setBookingStatus(bookingDTO.getBookingStatus());
        booking.setPrice(bookingDTO.getPrice());
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setUserEmail(bookingDTO.getUserEmail());
        booking.setUserName(bookingDTO.getUserName());
        booking.setExpirationDate(bookingDTO.getExpirationDate());
        return booking;
    }
}
