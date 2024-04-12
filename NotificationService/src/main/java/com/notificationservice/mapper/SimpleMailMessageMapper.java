package com.notificationservice.mapper;

import com.notificationservice.model.Notification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Objects;

@Component
public class SimpleMailMessageMapper {

    public Notification toNotification(SimpleMailMessage simpleMailMessage) {
        if (simpleMailMessage == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setMessage(simpleMailMessage.getText());
        notification.setSubject(simpleMailMessage.getSubject());
        notification.setEmail(Arrays.stream(Objects.requireNonNull(simpleMailMessage.getTo())).findFirst().get());
        return notification;
    }

    public SimpleMailMessage toSimpleMailMessage(Notification notification) {
        if (notification == null) {
            return null;
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(notification.getMessage());
        simpleMailMessage.setSubject(notification.getSubject());
        simpleMailMessage.setTo(notification.getEmail());

        return simpleMailMessage;
    }
}
