package com.example.BookingServiceUpdated.mapper;

import com.example.BookingServiceUpdated.dto.BookingDTO;
import com.example.BookingServiceUpdated.dto.BookingPaymentDTO;
import com.example.BookingServiceUpdated.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingPaymentMapper {
    public BookingPaymentDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingPaymentDTO bookingPayemntDTO = new BookingPaymentDTO();
        bookingPayemntDTO.setId(booking.getId());
        bookingPayemntDTO.setPrice(booking.getPrice());

        return bookingPayemntDTO;
    }
}
