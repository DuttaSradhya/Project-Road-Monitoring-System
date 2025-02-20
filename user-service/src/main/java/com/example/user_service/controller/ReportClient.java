package com.example.user_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("report-service")
public interface ReportClient {
    @GetMapping("api/report/search/user")
    List<Map<String, Object>> searchReportsByUser(@RequestParam("userId") String userId);

}
