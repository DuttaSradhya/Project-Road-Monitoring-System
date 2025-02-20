package com.example.report_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.report_service.model.ReportStatus;

public interface ReportStatusRepository extends MongoRepository<ReportStatus, String>{
    Optional<ReportStatus> findByStatus(String status);
}
