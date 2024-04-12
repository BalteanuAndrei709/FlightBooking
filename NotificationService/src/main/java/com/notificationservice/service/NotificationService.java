package com.notificationservice.service;

import com.notificationservice.kafka.KafkaProducerService;
import com.notificationservice.mapper.SimpleMailMessageMapper;
import com.notificationservice.model.Notification;
import com.notificationservice.model.NotificationStatus;
import com.notificationservice.repository.NotificationsRepository;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationsRepository notificationsRepository;
    private final BookingHandlingService bookingHandlingService;
    private final KafkaProducerService kafkaProducerService;
    private final SimpleMailMessageMapper simpleMailMessageMapper;
    private final MailSender mailSender;

    public NotificationService(NotificationsRepository notificationsRepository, BookingHandlingService bookingHandlingService, KafkaProducerService kafkaProducerService, SimpleMailMessageMapper simpleMailMessageMapper, MailSender mailSender) {
        this.notificationsRepository = notificationsRepository;
        this.bookingHandlingService = bookingHandlingService;
        this.kafkaProducerService = kafkaProducerService;
        this.simpleMailMessageMapper = simpleMailMessageMapper;
        this.mailSender = mailSender;
    }

    public void saveNotification(Notification notification){
        notificationsRepository.save(notification);
    }
    public boolean hasBeenSent(Notification notification){
        return notificationsRepository.findByRecipientIdAndAndBookingStatusAndStatus(notification.getRecipientId(),
                        notification.getBookingStatus(),
                        NotificationStatus.DELIVERED)
                .isPresent();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void safeDeleteDeliveredNotifications(){
        notificationsRepository.deleteByStatus(NotificationStatus.DELIVERED);
    }

    @Scheduled(cron="0 * * * * ?")
    public void resendFailedNotifications(){
        List<Notification> failedNotifications = notificationsRepository.findByStatus(NotificationStatus.FAILED);
        for(Notification notification: failedNotifications){
            notification.setRetryCount(notification.getRetryCount() + 1);
                if(notification.getRetryCount() <= 3)
                    kafkaProducerService.sendMessage(notification);
                else{
                    Notification failedSendingNotification = bookingHandlingService.sendFailedNotificationReceiving(notification.getEmail());
                    SimpleMailMessage simpleMailMessage = simpleMailMessageMapper.toSimpleMailMessage(failedSendingNotification);
                    mailSender.send(simpleMailMessage);
                    notificationsRepository.deleteById(notification.getId());
                }
            }
    }
}
