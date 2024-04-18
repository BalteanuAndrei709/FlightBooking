package com.adminservice.kafka.listener;
import com.adminservice.dto.BookingAdminStatusDTO;
import com.adminservice.dto.ReserveSeatsDTO;
import com.adminservice.kafka.producer.KafkaProducerService;
import com.adminservice.service.FlightService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

    private final FlightService flightService;
    private final KafkaProducerService kafkaProducerService;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumerService(FlightService flightService, KafkaProducerService kafkaProducerService) {
        this.flightService = flightService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "admin-reserve-seats", groupId = "admin-group")
    public void listen(ConsumerRecord<String, String> record) {

        try {
            ReserveSeatsDTO reserveSeatsDTO = objectMapper.readValue(record.value(), ReserveSeatsDTO.class);
            logger.info("Received booking: {}", reserveSeatsDTO);
            BookingAdminStatusDTO bookingAdminStatusDTO = new BookingAdminStatusDTO();
            bookingAdminStatusDTO.setBookingId(reserveSeatsDTO.getBookingId());
            try {
                flightService.decrementSeatsAvailable(reserveSeatsDTO);
                bookingAdminStatusDTO.setBookingStatus(true);
            }
            catch (Exception e){
                bookingAdminStatusDTO.setBookingStatus(false);
            }
            finally {
                kafkaProducerService.sendMessage("admin-reserve-seats-status", bookingAdminStatusDTO);
            }
        } catch(Exception e) {
            logger.error("Received booking: {}", e.getMessage());
        }
    }
}
