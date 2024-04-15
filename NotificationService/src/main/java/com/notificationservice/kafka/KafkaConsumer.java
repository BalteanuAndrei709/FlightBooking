package com.notificationservice.kafka;

import com.google.gson.Gson;
import com.notificationservice.dto.NotificationDTO;
import com.notificationservice.mapper.NotificationMapper;
import com.notificationservice.mapper.SimpleMailMessageMapper;
import com.notificationservice.model.Notification;
import com.notificationservice.model.NotificationStatus;
import com.notificationservice.service.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final NotificationService notificationService;
    private final JavaMailSender mailSender;
    private final SimpleMailMessageMapper simpleMailMessageMapper;
    private final NotificationMapper notificationMapper;

    public KafkaConsumer(NotificationService notificationService, JavaMailSender mailSender, SimpleMailMessageMapper simpleMailMessageMapper, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.mailSender = mailSender;
        this.simpleMailMessageMapper = simpleMailMessageMapper;
        this.notificationMapper = notificationMapper;
    }

    @KafkaListener(topics = "notifications", groupId = "myGroup") //, containerFactory = "concurrentKafkaListenerContainerFactory"
    public void consume(ConsumerRecord<String, NotificationDTO> record) {

        Gson gson = new Gson();
        NotificationDTO topicNotification = gson.fromJson(String.valueOf(record.value()), NotificationDTO.class);

        if(notificationService.hasBeenSent(topicNotification))
            return;

        Notification notification = notificationMapper.toEntity(topicNotification);

        try{

            System.out.println("message = " + topicNotification);
            // save the notification with a status of "pending"

            SimpleMailMessage simpleMailMessage = simpleMailMessageMapper.toSimpleMailMessage(notification);
            notificationService.saveNotification(notification);
            // attempt to send the email
            mailSender.send(simpleMailMessage);
            // if successful, update the notification with a status of "DELIVERED"
            notification.setStatus(NotificationStatus.DELIVERED);
            /*
            for testing purpose, it is not necessary to save it if it was delivered
            ->can do a scheduled job to delete all delivered notifications from db or not save them at all
             */
            notificationService.saveNotification(notification);
            // acknowledge the message only after successful processing
            //acknowledgment.acknowledge();
        } catch (Exception e){ // eroare pe salvare pending
            notification.setStatus(NotificationStatus.FAILED);
            notificationService.saveNotification(notification);
            //acknowledgment.acknowledge();
        }
    }
}
