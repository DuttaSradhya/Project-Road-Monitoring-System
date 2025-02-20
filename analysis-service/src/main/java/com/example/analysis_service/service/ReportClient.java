package com.example.analysis_service.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.analysis_service.model.Report;

@FeignClient(name = "report-service")
public interface ReportClient {
    @GetMapping("/api/report")
    List<Report> getAllReports();
}
