package com.example.analysis_service.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.analysis_service.model.Location;
import com.example.analysis_service.model.Report;

@Service
public class AnalysisService {

    public long getTotalReports(List<Report> reports){
        return reports.size();
    }

    public Map<String, Long> getReportsByCategory(List<Report> reports){
        return reports.stream().collect(Collectors.groupingBy((Report report)-> report.getCategory().getName(),Collectors.counting()));
    }

    public Map<String, Long> getReportsByStatus(List<Report> reports){
        return reports.stream()
        .collect(Collectors.groupingBy(report -> 
            report.getStatus() == null || report.getStatus().getStatus() == null ? "Unknown" : report.getStatus().getStatus(), 
            Collectors.counting()));   
    }
    public List<Location> getAllLocations(List<Report> reports) {
        return reports.stream()
                .map(Report::getLocation)
                .collect(Collectors.toList());
    }
}
