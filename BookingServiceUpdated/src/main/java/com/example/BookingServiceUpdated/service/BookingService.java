package com.example.BookingServiceUpdated.service;

import com.example.BookingServiceUpdated.dto.BookingAdminDTO;
import com.example.BookingServiceUpdated.dto.BookingPaymentDTO;
import com.example.BookingServiceUpdated.dto.CompressedBookingDTO;
import com.example.BookingServiceUpdated.dto.NotificationMessageDTO;
import com.example.BookingServiceUpdated.kafka.producer.KafkaProducerService;
import com.example.BookingServiceUpdated.mapper.BookingMapper;
import com.example.BookingServiceUpdated.model.Booking;
import com.example.BookingServiceUpdated.model.Flight;
import com.example.BookingServiceUpdated.model.Operator;
import com.example.BookingServiceUpdated.repository.BookingRepository;
import com.example.BookingServiceUpdated.repository.FlightRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final BookingMapper bookingMapper;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          FlightRepository flightRepository,
                          BookingMapper bookingMapper,
                          KafkaProducerService kafkaProducerService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.kafkaProducerService = kafkaProducerService;
        this.flightRepository = flightRepository;
    }

    /**
     * Handles the main processing of a booking. Sends the message to the Admin Service for
     * checking if there are enough available seats. In case of success, it sends another message to
     * Payment Service to complete the payment of the booking. At every step, a message is sent to
     * Notification Service.
     * @param bookingDTO
     * The information about the booking and the user which books the flight.
     */
    @Transactional
    public void createBooking(CompressedBookingDTO bookingDTO) {
        Optional<Flight> flight = flightRepository.findById(bookingDTO.getFlightId());
        if(flight.isEmpty()){
            throw new RuntimeException("No flight with id " + bookingDTO.getFlightId());
        }
        Booking booking = bookingMapper.toEntity(bookingDTO, flight.get());
        booking = bookingRepository.save(booking).block();
        sendForAdminCheck(Objects.requireNonNull(booking));
    }

    /**
     * Sends trough kafka a BookingPaymentDTO in order to complete the payment.
     * @param bookingId
     */
    public void sendForPaymentCheck(String bookingId){
        BookingPaymentDTO bookingPaymentDTO = createBookingPaymentDTO(bookingId);
        kafkaProducerService.sendMessage("payment-check", bookingPaymentDTO);
    }

    /**
     * Creates a BookingPaymentDTO from the bookingId. The DTO will be sent trough kafka for processing
     * the payment.
     * @param bookingId
     * The id of the booking which will be used for retrieving information needed for payment.
     * @return
     * An instance of BookingPaymentDTO.
     */
    public BookingPaymentDTO createBookingPaymentDTO(String bookingId){
        Booking booking = bookingRepository.findById(bookingId).block();
        if(booking == null){
            throw new RuntimeException("No booking found with id " + bookingId);
        }
        Optional<Flight> flight = flightRepository.findById(booking.getFlightId());
        if(flight.isEmpty()){
            throw new RuntimeException("No flight with id " + booking.getFlightId());
        }
        Operator operator = flight.get().getOperator();
        return new BookingPaymentDTO(
                bookingId, booking.getPrice(), operator.getIban()
        );
    }

    /**
     * Method that will send the message to Admin Service, which will check if there are enough
     * available seats. It will also wait for the response of this check.
     * @param booking
     * The information about booking.
     */
    private void sendForAdminCheck(Booking booking) {
        BookingAdminDTO bookingAdminDTO = bookingMapper.entityToBookingAdminDTO(booking);
        kafkaProducerService.sendMessage("admin-check", bookingAdminDTO);
    }

    /**
     * Sends trough kafka a Notification regarding the failure for reserving tickets.
     * @param bookingId
     */
    public void sendNotification(String bookingId, String message, Boolean error) {
        Booking booking = bookingRepository.findById(bookingId).block();
        if(booking == null){
            throw new RuntimeException("No booking found with id " + bookingId);
        }
        NotificationMessageDTO notificationMessageDTO = new NotificationMessageDTO(
                message, error, booking.getEmail()
        );
        kafkaProducerService.sendMessage("notification", notificationMessageDTO);
    }
}

