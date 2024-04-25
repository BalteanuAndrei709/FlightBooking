package com.notificationservice.service;

import com.notificationservice.dto.NotificationDTO;
import com.notificationservice.kafka.notifications.KafkaProducerService;
import com.notificationservice.mapper.NotificationMapper;
import com.notificationservice.mapper.SimpleMailMessageMapper;
import com.notificationservice.model.Notification;
import com.notificationservice.model.NotificationStatus;
import com.notificationservice.repository.NotificationsRepository;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationsRepository notificationsRepository;
    private final BookingHandlingService bookingHandlingService;
    private final KafkaProducerService kafkaProducerService;
    private final SimpleMailMessageMapper simpleMailMessageMapper;
    private final MailSender mailSender;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationsRepository notificationsRepository, BookingHandlingService bookingHandlingService, KafkaProducerService kafkaProducerService, SimpleMailMessageMapper simpleMailMessageMapper, MailSender mailSender, NotificationMapper notificationMapper) {
        this.notificationsRepository = notificationsRepository;
        this.bookingHandlingService = bookingHandlingService;
        this.kafkaProducerService = kafkaProducerService;
        this.simpleMailMessageMapper = simpleMailMessageMapper;
        this.mailSender = mailSender;
        this.notificationMapper = notificationMapper;
    }

    public void saveNotification(Notification notification){

        notificationsRepository.save(notification);
    }
    public boolean hasBeenSent(NotificationDTO notification){
        return notificationsRepository.findByRecipientIdAndBookingStatusAndStatus(notification.getRecipientId(),
                        notification.getBookingStatus(),
                        NotificationStatus.DELIVERED)
                .isPresent();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void safeDeleteDeliveredNotifications(){
        notificationsRepository.deleteByStatus(NotificationStatus.DELIVERED);
    }

    @Scheduled(cron="0 * * * * ?")
    public void resendFailedNotifications() {
        List<Notification> failedNotifications = notificationsRepository.findByStatus(NotificationStatus.FAILED);
        List<NotificationDTO> failedNotificationsDTO = failedNotifications.stream()
                        .map(notificationMapper::toDTO)
                        .toList();
        if (!failedNotificationsDTO.isEmpty()){
            for (NotificationDTO notification : failedNotificationsDTO) {
                notification.setRetryCount(notification.getRetryCount() + 1);
                if (notification.getRetryCount() <= 3) {
                    kafkaProducerService.sendMessage(notification);
                } else {
                    NotificationDTO failedSendingNotification = bookingHandlingService.sendFailedNotificationReceiving(notification.getEmail(), notification.getRecipientId());
                    SimpleMailMessage simpleMailMessage = simpleMailMessageMapper.toSimpleMailMessage(notificationMapper.toEntity(failedSendingNotification));
                    mailSender.send(simpleMailMessage);
                    notificationsRepository.deleteById(notification.getId());
                }
            }
    }
    }
}
