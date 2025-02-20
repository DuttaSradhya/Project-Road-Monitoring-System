package com.example.notification_service.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.notification_service.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationConsumer {
    @Autowired
    private MailService mailService;
    @KafkaListener(topics = "notification", groupId = "my-group-id")
    public void listen(String notification){
        Map<String, Object> model = new HashMap<>();
        model.put("notification", notification);

        mailService.sendMail(model);
        log.info(notification);
    }
}
