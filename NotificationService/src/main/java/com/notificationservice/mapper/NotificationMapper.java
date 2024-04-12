package com.notificationservice.mapper;

import com.notificationservice.model.Notification;
import com.notificationservice.dto.NotificationDTO;

public class NotificationMapper {

    public NotificationDTO toDTO(Notification notification) {
        if (notification == null) {
            return null;
        }

        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setStatus(notification.getStatus());
        dto.setRetryCount(notification.getRetryCount());
        dto.setRecipientId(notification.getRecipientId());
        dto.setMessage(notification.getMessage());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setUpdatedAt(notification.getUpdatedAt());
        return dto;
    }

    public Notification toEntity(NotificationDTO dto) {
        if (dto == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setId(dto.getId());
        notification.setStatus(dto.getStatus());
        notification.setRetryCount(dto.getRetryCount());
        notification.setRecipientId(dto.getRecipientId());
        notification.setMessage(dto.getMessage());
        notification.setCreatedAt(dto.getCreatedAt());
        notification.setUpdatedAt(dto.getUpdatedAt());
        return notification;
    }
}

