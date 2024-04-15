package com.notificationservice.mapper;

import com.notificationservice.model.Notification;
import com.notificationservice.dto.NotificationDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
        dto.setEmail(notification.getEmail());
        dto.setSubject(notification.getSubject());
        dto.setBookingStatus(notification.getBookingStatus());
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
        notification.setBookingStatus(dto.getBookingStatus());
        notification.setEmail(dto.getEmail());
        notification.setSubject(dto.getSubject());
        return notification;
    }

    public List<NotificationDTO> toDTOList(List<Notification> notifications) {
        List<NotificationDTO> dtos = new ArrayList<>();
        for (Notification notification : notifications) {
            dtos.add(toDTO(notification));
        }
        return dtos;
    }

    public List<Notification> toEntityList(List<NotificationDTO> dtos) {
        List<Notification> notifications = new ArrayList<>();
        for (NotificationDTO dto : dtos) {
            notifications.add(toEntity(dto));
        }
        return notifications;
    }
}

