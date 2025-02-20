package com.example.report_service.service;


//import org.hibernate.mapping.List;
import java.util.List;
import java.util.Optional;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.report_service.dto.ReportRequest;
import com.example.report_service.dto.ReportResponse;
import com.example.report_service.model.Category;
import com.example.report_service.model.FeedBack;
import com.example.report_service.model.Location;
import com.example.report_service.model.Report;
import com.example.report_service.model.ReportStatus;
import com.example.report_service.repository.CategoryRepository;
import com.example.report_service.repository.FeedbackRepository;
import com.example.report_service.repository.LocationRepository;
import com.example.report_service.repository.ReportRepository;
import com.example.report_service.repository.ReportStatusRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final ReportStatusRepository statusRepository;
    private final FeedbackRepository feedbackRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void createReport(ReportRequest reportRequest){

        Optional<Category> category = categoryRepository.findById(reportRequest.getCategoryId());
        Category categoryRef = category.get();

        Location location = Location.builder()
            .latitude(reportRequest.getLatitude())
            .longitude(reportRequest.getLongitude())
            .build();
        Location locatationRef = locationRepository.save(location);
        
        ReportStatus status = ReportStatus.builder()
            .status(reportRequest.getStatus())
            .build();
        ReportStatus statusRef = statusRepository.save(status);   
        Report report = Report.builder()
            .userId(reportRequest.getUserId())
            .photoUrl(reportRequest.getPhotoUrl())
            .description(reportRequest.getDescription())
            .location(locatationRef)
            .category(categoryRef)
            .status(statusRef)
            .build();

        reportRepository.save(report);
        kafkaTemplate.send("notification", "A new report has been submitted");
        log.info("Report {} is saved", report.getId());

    } 

    public List<ReportResponse> getAllReports(){
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(report -> mapToReportResponse(report)).toList();
    }

    private ReportResponse mapToReportResponse(Report report){
        return ReportResponse.builder()
            .id(report.getId())
            .userId(report.getUserId())
            .photoUrl(report.getPhotoUrl())
            .description(report.getDescription())
            .location(report.getLocation())
            .category(report.getCategory())
            .status(report.getStatus())
            .feedBack(report.getFeedBack())
            .build(); 
    }

    public ReportResponse getReportById(String id) {
        Optional<Report> reportOptional = reportRepository.findById(id);

        Report report = reportOptional.get();
        return mapToReportResponse(report);
    }

    public void updateReportById(String id, ReportRequest reportRequest){
        Optional<Report> reportOptional = reportRepository.findById(id);
        Report report = reportOptional.get();

        ReportStatus status = ReportStatus.builder()
            .status(reportRequest.getStatus())
            .build();
        report.setStatus(status);
        reportRepository.save(report);
        log.info("Report {} is updated", report.getId());
    }

    public void updateStatus(String reportId, String statusId) {
        Optional<ReportStatus> status = statusRepository.findById(statusId);
        ReportStatus updatedStatus = status.get();

        Optional<Report> report = reportRepository.findById(reportId);
        Report updatedReport = report.get();

        updatedReport.setStatus(updatedStatus);
        reportRepository.save(updatedReport);
        log.info("Report {} has been updated to {}", updatedReport.getId(), updatedStatus.getStatus());
    }

    public void createFeedback(String reportId, FeedBack feedBack) {
        Optional<Report> report = reportRepository.findById(reportId);
        Report reportFeedback = report.get();
        FeedBack feedbackRef = feedbackRepository.save(feedBack);
        reportFeedback.setFeedBack(feedbackRef);
        reportRepository.save(reportFeedback);

    }

    public FeedBack getFeedBack(String reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        return report.get().getFeedBack();
    }

    public List<ReportResponse> searchReportsByDescription(String description) {
        List<Report> reports = reportRepository.findByDescriptionContainingIgnoreCase(description);
        return reports.stream().map(this::mapToReportResponse).toList();
    }

    public List<ReportResponse> searchReportsByCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if (category.isPresent()) {
            List<Report> reports = reportRepository.findByCategory(category.get());
            return reports.stream().map(this::mapToReportResponse).toList();
        } else {
            return List.of();
        }
    }

    public List<ReportResponse> searchReportsByStatus(String status) {
        Optional<ReportStatus> reportStatus = statusRepository.findByStatus(status);
        if (reportStatus.isPresent()) {
            List<Report> reports = reportRepository.findByStatus(reportStatus.get());
            return reports.stream().map(this::mapToReportResponse).toList();
        } else {
            return List.of();
        }
    }

    public List<ReportResponse> searchReportsByUser(String userId) {
        List<Report> reports = reportRepository.findByUserId(userId);
        return reports.stream().map(this::mapToReportResponse).toList();
    }

    public void updateCategory(String reportId, String categoryId) {
        log.info(categoryId);
        log.info(reportId);

        Optional<Category> status = categoryRepository.findById(categoryId);
        Category updatedStatus = status.get();

        Optional<Report> report = reportRepository.findById(reportId);
        Report updatedReport = report.get();

        updatedReport.setCategory(updatedStatus);
        reportRepository.save(updatedReport);
        log.info("Report {} has been updated to {}", updatedReport.getId(), updatedStatus.getName());;
    }
}

