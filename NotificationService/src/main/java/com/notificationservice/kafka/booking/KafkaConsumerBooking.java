package com.notificationservice.kafka.booking;

import com.google.gson.*;
import com.notificationservice.dto.BookingDTO;
import com.notificationservice.mapper.BookingMapper;
import com.notificationservice.model.Booking;
import com.notificationservice.service.BookingHandlingService;
import com.notificationservice.service.ReceivedBookingsService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class KafkaConsumerBooking {

    private final BookingMapper bookingMapper;
    private final BookingHandlingService bookingService;
    private final ReceivedBookingsService receivedBookingsService;

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
            (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                    LocalDate.parse(json.getAsJsonPrimitive().getAsString())).create();

    public KafkaConsumerBooking(BookingMapper bookingMapper, BookingHandlingService bookingService, ReceivedBookingsService receivedBookingsService) {
        this.bookingMapper = bookingMapper;
        this.bookingService = bookingService;
        this.receivedBookingsService = receivedBookingsService;
    }

    @KafkaListener(topics = "bookings", groupId = "myGroup")
    public void consume(ConsumerRecord<String, BookingDTO> record) {
        try{
            BookingDTO bookingDTO = gson.fromJson(String.valueOf(record.value()), BookingDTO.class);
            receivedBookingsService.saveReceivedBooking(bookingMapper.fromDTO(bookingDTO));
            Booking booking = bookingMapper.fromDTO(bookingDTO);
            bookingService.handleBookingInitialized(booking);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
