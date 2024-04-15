package com.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.notificationservice.model.NotificationStatus;

import java.util.Date;

public class NotificationDTO {
    private String id;
    private NotificationStatus status;
    private Integer retryCount;
    private Integer recipientId;
    private String message;
    private Date createdAt;
    private Date updatedAt;
    private String subject;
    private String email;
    private String bookingStatus;

    public NotificationDTO() {
    }

    public NotificationDTO(String id, NotificationStatus status, Integer retryCount, Integer recipientId, String message, Date createdAt, Date updatedAt, String subject, String email, String bookingStatus) {
        this.id = id;
        this.status = status;
        this.retryCount = retryCount;
        this.recipientId = recipientId;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subject = subject;
        this.email = email;
        this.bookingStatus = bookingStatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", retryCount=" + retryCount +
                ", recipientId=" + recipientId +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", subject='" + subject + '\'' +
                ", email='" + email + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }
}
