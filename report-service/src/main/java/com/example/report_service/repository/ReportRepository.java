package com.example.report_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.report_service.model.Category;
import com.example.report_service.model.Report;
import com.example.report_service.model.ReportStatus;

public interface ReportRepository extends MongoRepository<Report, String>{
    List<Report> findByDescriptionContainingIgnoreCase(String description);
    List<Report> findByCategory(Category category);
    List<Report> findByStatus(ReportStatus status);
    List<Report> findByUserId(String userId);

}
