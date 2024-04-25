package com.notificationservice.repository;

import com.notificationservice.model.Notification;
import com.notificationservice.model.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationsRepository extends MongoRepository<Notification, String> {

    Optional<Notification> findByRecipientIdAndBookingStatusAndStatus(Integer id, String bookingStatus, NotificationStatus status);
    Optional<Notification> findByRecipientIdAndStatus(Integer id, NotificationStatus status);
    List<Notification> findByStatus(NotificationStatus status);

    Notification findByRecipientId(Integer id);
    void deleteByStatus(NotificationStatus status);

}
