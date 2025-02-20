package com.example.notification_service.dto;

import lombok.Data;

@Data
public class MailRequest {
    private String to;
    private String from;
    private String subject;
}
