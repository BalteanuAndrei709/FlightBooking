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
    private final NotificationService notificationService;

    public NotificationController(BookingHandlingService bookingHandlingService, NotificationService notificationService) {
        this.bookingHandlingService = bookingHandlingService;
        this.notificationService = notificationService;
    }

    @PostMapping("/booking")
    private ResponseEntity<String> sendNotification(@RequestBody Booking booking){
        bookingHandlingService.handleBookingInitialized(booking);
        return new ResponseEntity<>("Notification has been sent", HttpStatus.OK);
    }


    //test
    @PostMapping
    private ResponseEntity<?> addNotification(@RequestBody Notification notification){
        notificationService.saveNotification(notification);
        return new ResponseEntity<>("Notification added.", HttpStatus.CREATED);
    }
}
