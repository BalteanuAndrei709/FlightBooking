package com.notificationservice.controller;

import com.notificationservice.mock.model.Booking;
import com.notificationservice.model.Notification;
import com.notificationservice.service.BookingHandlingService;
import com.notificationservice.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final BookingHandlingService bookingHandlingService;

    public NotificationController(BookingHandlingService bookingHandlingService) {
        this.bookingHandlingService = bookingHandlingService;

    }

    @PostMapping("/booking")
    private ResponseEntity<String> sendNotification(@RequestBody Booking booking){
        bookingHandlingService.handleBookingInitialized(booking);
        return new ResponseEntity<>("Notification has been sent", HttpStatus.OK);
    }

}
