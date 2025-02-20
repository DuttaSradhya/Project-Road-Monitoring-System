package com.example.notification_service.config;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("authority-service")
public interface AuthorityClient {
    @GetMapping("/api/admin/emails")
    List<String> getMail();
}
