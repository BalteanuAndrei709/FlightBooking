package com.notificationservice.service;

import com.notificationservice.kafka.KafkaProducerService;
import com.notificationservice.mapper.SimpleMailMessageMapper;
import com.notificationservice.mock.model.Booking;
import com.notificationservice.model.Notification;
import com.notificationservice.model.NotificationStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BookingHandlingService {

    private final KafkaProducerService kafkaProducerService;
    private final SimpleMailMessageMapper simpleMailMessageMapper;

    public BookingHandlingService(KafkaProducerService kafkaProducerService, SimpleMailMessageMapper simpleMailMessageMapper) {
        this.kafkaProducerService = kafkaProducerService;
        this.simpleMailMessageMapper = simpleMailMessageMapper;
    }

    public void handleBookingInitialized(Booking booking){
        if("PENDING".equals(booking.getBookingStatus())){
            sendInitializationEmail(booking.getUserEmail(), booking.getExpirationDate(), booking.getFlightId(), booking.getBookingStatus());
        }
        if("CONFIRMED".equals(booking.getBookingStatus())){
            sendConfirmedPaymentEmail(booking.getUserEmail(), booking.getFlightId(), booking.getBookingStatus());
        }
        if("CHECKED_IN".equals(booking.getBookingStatus())){
            sendCheckInEmail(booking.getUserEmail(), booking.getExpirationDate(), booking.getFlightId(), booking.getBookingStatus());
        }
        if("CANCELED".equals(booking.getBookingStatus())){
            sendCanceledPaymentEmail(booking.getUserEmail(), booking.getFlightId(), booking.getBookingStatus());
        }
        if("COMPLETED".equals(booking.getBookingStatus())){
            sendCompletedBookingEmail(booking.getUserEmail(), booking.getFlightId(), booking.getBookingStatus());
        }
    }

    public void sendCompletedBookingEmail(String userEmail,Integer flightId, String bookingStatus){
        String emailSubject = "Completed Booking.";
        String emailText = "Hello,\n\nYour Booking has been confirmed. You will receive your booking ticket(s) soon!\n\nThank you!";
        customNotificationBuilder(userEmail, emailSubject, emailText, flightId, bookingStatus);

    }

    public void sendCanceledPaymentEmail(String userEmail,Integer flightId, String bookingStatus){
        String emailSubject = "Canceled Payment.";
        String emailText = "Hello,\n\nYour payment couldn't be confirmed. You will have to initialize the booking process again!\n\nThank you!";
        customNotificationBuilder(userEmail, emailSubject, emailText, flightId, bookingStatus);
    }

    public void sendConfirmedPaymentEmail(String userEmail, Integer flightId, String bookingStatus){
        String emailSubject = "Payment Confirmed!";
        String emailText = "Hello,\n\nYour payment has been confirmed. We will confirm your booking soon!\n\nThank you!";
        customNotificationBuilder(userEmail, emailSubject, emailText, flightId, bookingStatus);
    }

    private void sendInitializationEmail(String userEmail, LocalDate expirationDate, Integer flightId, String bookingStatus){
        String formattedExpirationTime = expirationDate.format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        String emailSubject = "Complete your Booking!";
        String emailText = String.format(
                "Hello,\n\nYour booking has been initialized. Please complete your payment by %s to avoid cancellation.\n\nThank you!",
                formattedExpirationTime);
        customNotificationBuilder(userEmail, emailSubject, emailText, flightId, bookingStatus);
    }

    private void sendCheckInEmail(String userEmail, LocalDate expirationDate, Integer flightId, String bookingStatus){
        String formattedExpirationTime = expirationDate.plusDays(7).format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        String emailSubject = "Check in your Booking!";
        String emailText = String.format(
                "Hello,\n\nYour booking has been completed. You can check in for free until %s.\n\nThank you!",
                formattedExpirationTime);
        customNotificationBuilder(userEmail, emailSubject, emailText, flightId, bookingStatus);
    }

    public Notification sendFailedNotificationReceiving(String userEmail){
        String emailSubject = "Something went wrong!";
        String emailText = "Hello,\n\nPlease contact our support, something went wrong with your booking process.\n\nThank you!";
        SimpleMailMessage message = messageBuilder(userEmail, emailSubject, emailText);
        return simpleMailMessageMapper.toNotification(message);
    }

    private void customNotificationBuilder(String userEmail, String emailSubject, String emailText, Integer flightId, String bookingStatus){
        SimpleMailMessage message = messageBuilder(userEmail, emailSubject, emailText);
        Notification notification = simpleMailMessageMapper.toNotification(message);
        notification.setStatus(NotificationStatus.PENDING);
        notification.setRecipientId(flightId);
        notification.setBookingStatus(bookingStatus);
        notification.setRetryCount(1);
        kafkaProducerService.sendMessage(notification);
    }

    private SimpleMailMessage messageBuilder(String userEmail, String emailSubject, String emailText){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject(emailSubject);
        message.setText(emailText);

        return message;
    }
}
