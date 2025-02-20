package com.example.authority_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("report-service")
public interface ReportClient {
    @GetMapping("/api/report/search/description")
    List<Map<String, Object>> searchReportsByDescription(@RequestParam("description") String description);

    @GetMapping("/api/report/search/category")
    List<Map<String, Object>> searchReportsByCategory(@RequestParam("categoryName") String categoryName);

    @GetMapping("/api/report/search/status")
    List<Map<String, Object>> searchReportsByStatus(@RequestParam("status") String status);


}
