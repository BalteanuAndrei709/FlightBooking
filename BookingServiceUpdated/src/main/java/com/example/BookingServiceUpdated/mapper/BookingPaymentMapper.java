package com.example.BookingServiceUpdated.mapper;

import com.example.BookingServiceUpdated.dto.BookingPaymentDTO;
import com.example.BookingServiceUpdated.model.Booking;
import com.example.BookingServiceUpdated.model.Operator;
import org.springframework.stereotype.Component;

@Component
public class BookingPaymentMapper {
    public BookingPaymentDTO toDTO(Booking booking, Operator operator) {
        if (booking == null) {
            return null;
        }

        BookingPaymentDTO bookingPayemntDTO = new BookingPaymentDTO();
        bookingPayemntDTO.setBookingId(booking.getId());
        bookingPayemntDTO.setPrice(booking.getPrice());
        bookingPayemntDTO.setIban(operator.getIban());

        return bookingPayemntDTO;
    }
}
