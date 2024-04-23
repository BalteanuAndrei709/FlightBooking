package com.payment.paymentservice.kafka.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.paymentservice.dto.BookingDTO;
import com.payment.paymentservice.dto.BookingPaymentDTO;
import com.payment.paymentservice.dto.BookingStatusDTO;
import com.payment.paymentservice.kafka.producer.KafkaProducerService;
import com.payment.paymentservice.model.BookingStatus;
import com.payment.paymentservice.model.OrderStatus;
import com.payment.paymentservice.model.PaymentOrder;
import com.payment.paymentservice.service.OrderService;
import com.payment.paymentservice.service.PayPalService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private PayPalService payPalService;
    @Autowired
    private OrderService orderService;



    @KafkaListener(topics = "bookings-status-payments", groupId = "admin-group-payments")
    public void listen(ConsumerRecord<String, String> record) {

        try {
            BookingPaymentDTO bookingPaymentDTO = objectMapper.readValue(record.value(), BookingPaymentDTO.class);
            BookingStatusDTO bookingStatusDTO = new BookingStatusDTO();
            String bookingId = bookingPaymentDTO.getId();
            bookingStatusDTO.setId(bookingId);
            logger.info("Received booking: {}", bookingPaymentDTO);
            double bookingPrice = bookingPaymentDTO.getPrice();
            Mono<PaymentOrder> paymentOrderMono = payPalService.createPayment(bookingPrice, "RO86TRM");
            paymentOrderMono.doOnNext(event -> {
                orderService.findByOrderId(event.getPayId()).subscribe(order -> {
                    String status = order.getStatus();
                    if (status.equals("INITIATED") ){
                        bookingStatusDTO.setBookingStatus(BookingStatus.PENDING);
                        logger.info("Status booking updated: {}", bookingStatusDTO.getBookingStatus());
                        kafkaProducerService.sendMessage(bookingStatusDTO);
                    }
                });
            });


        } catch(Exception e) {
            logger.error("Received booking: {}", e.getMessage());
        }

    }
}
