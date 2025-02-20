package com.example.analysis_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.analysis_service.model.Location;
import com.example.analysis_service.model.Report;
import com.example.analysis_service.service.AnalysisService;
import com.example.analysis_service.service.ReportService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/total-reports")
    @CircuitBreaker(name = "analysis", fallbackMethod = "totalReportsFallback")
    public long getTotalReports() {
        
        List<Report> reports = reportService.fetchAllReports();
        return analysisService.getTotalReports(reports);
    }

    @GetMapping("/reports-by-status")
    // @CircuitBreaker(name = "analysis", fallbackMethod = "breakingMethod")
    public Map<String, Long> getReportsByStatus() {
        List<Report> reports = reportService.fetchAllReports();
        return analysisService.getReportsByStatus(reports);
    }

    @GetMapping("/reports-by-category")
    // @CircuitBreaker(name = "analysis", fallbackMethod = "breakingMethod")
    public Map<String, Long> getReportsByCategory() {
        List<Report> reports = reportService.fetchAllReports();
        return analysisService.getReportsByCategory(reports);
    }

    public long totalReportsFallback(Throwable throwable) {
        // Handle fallback for getTotalReports
        log.info("Fallback for getTotalReports: " + throwable.getMessage());
        return 0L; // Default value or handle appropriately
    }

    @GetMapping("/getAllLocations")
    public List<Location> getAllLocations() {
        List<Report> reports = reportService.fetchAllReports();
        return analysisService.getAllLocations(reports);
    }
}
