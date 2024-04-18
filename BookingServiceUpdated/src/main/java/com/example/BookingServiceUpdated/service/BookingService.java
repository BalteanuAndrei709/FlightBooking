package com.example.BookingServiceUpdated.service;

import com.example.BookingServiceUpdated.dto.BookingAdminStatusDTO;
import com.example.BookingServiceUpdated.dto.CompressedBookingDTO;
import com.example.BookingServiceUpdated.dto.ReserveSeatsDTO;
import com.example.BookingServiceUpdated.kafka.producer.KafkaProducerService;
import com.example.BookingServiceUpdated.mapper.BookingMapper;
import com.example.BookingServiceUpdated.model.Booking;
import com.example.BookingServiceUpdated.model.BookingChecks;
import com.example.BookingServiceUpdated.repository.BookingChecksRepository;
import com.example.BookingServiceUpdated.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.*;

@Service
public class BookingService {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private BookingRepository bookingRepository;
    private BookingChecksRepository bookingChecksRepository;
    private BookingMapper bookingMapper;
    private KafkaProducerService kafkaProducerService;
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          BookingChecksRepository bookingChecksRepository,
                          BookingMapper bookingMapper,
                          KafkaProducerService kafkaProducerService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.kafkaProducerService = kafkaProducerService;
        this.bookingChecksRepository = bookingChecksRepository;
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
    public boolean createBooking(CompressedBookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking.setBookingChecks(new BookingChecks());
        booking = bookingRepository.save(booking).block();

        boolean reserved = reserveSeats(Objects.requireNonNull(booking));
        return reserved;
    }

    /**
     * Method that will send the message to Admin Service, which will check if there are enough
     * available seats. It will also wait for the response of this check.
     * @param booking
     */
    private boolean reserveSeats(Booking booking) {
        ReserveSeatsDTO reserveSeatsDTO = bookingMapper.entityToReserveSeatsDTO(booking);
        kafkaProducerService.sendMessage("admin-reserve-seats", reserveSeatsDTO);
        return waitForAdminStatus(reserveSeatsDTO.getBookingId());
    }

    private Boolean waitForAdminStatus(String bookingId){
        try {
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            scheduler.scheduleAtFixedRate(() -> {
                Booking booking = bookingRepository.findById(bookingId).block();
                if (booking != null && booking.getBookingChecks().getAdminCheck()) {
                    future.complete(true);
                }
            }, 0, 500, TimeUnit.MILLISECONDS);
            return future.get(10, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            return false;
        }
    }

    public void updateBookingChecks(BookingAdminStatusDTO adminStatusDTO) {
        Optional<Booking> bookingOptional =
                bookingRepository.findById(adminStatusDTO.getBookingId()).blockOptional();
        if (bookingOptional.isEmpty()) {
            throw new RuntimeException("No booking found with id " + adminStatusDTO.getBookingId());
        }
        var booking = bookingOptional.get();
        booking.getBookingChecks().setAdminCheck(adminStatusDTO.getBookingStatus());
        bookingRepository.save(booking).subscribe();
    }
}

