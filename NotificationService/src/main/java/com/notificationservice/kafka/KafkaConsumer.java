package com.notificationservice.kafka;

import com.google.gson.Gson;
import com.notificationservice.mapper.SimpleMailMessageMapper;
import com.notificationservice.model.Notification;
import com.notificationservice.model.NotificationStatus;
import com.notificationservice.service.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final NotificationService notificationService;
    private final JavaMailSender mailSender;
    private final SimpleMailMessageMapper simpleMailMessageMapper;

    public KafkaConsumer(NotificationService notificationService, JavaMailSender mailSender, SimpleMailMessageMapper simpleMailMessageMapper) {
        this.notificationService = notificationService;
        this.mailSender = mailSender;
        this.simpleMailMessageMapper = simpleMailMessageMapper;
    }

    @KafkaListener(topics = "notifications", groupId = "myGroup") //, containerFactory = "concurrentKafkaListenerContainerFactory"
    public void consume(ConsumerRecord<String, Notification> record) {

        Gson gson = new Gson();
        Notification topicNotification = gson.fromJson(String.valueOf(record.value()), Notification.class);

        if(notificationService.hasBeenSent(topicNotification))
            return;

        try{

            System.out.println("message = " + topicNotification);
            // save the notification with a status of "pending"
            SimpleMailMessage simpleMailMessage = simpleMailMessageMapper.toSimpleMailMessage(topicNotification);
            notificationService.saveNotification(topicNotification);
            // attempt to send the email
            mailSender.send(simpleMailMessage);
            // if successful, update the notification with a status of "DELIVERED"
            topicNotification.setStatus(NotificationStatus.DELIVERED);
            /*
            for testing purpose, it is not necessary to save it if it was delivered
            ->can do a scheduled job to delete all delivered notifications from db or not save them at all
             */
            notificationService.saveNotification(topicNotification);
            // acknowledge the message only after successful processing
            //acknowledgment.acknowledge();
        } catch (MailException e){
            topicNotification.setStatus(NotificationStatus.FAILED);
            notificationService.saveNotification(topicNotification);
            //acknowledgment.acknowledge();
        }
    }
}
