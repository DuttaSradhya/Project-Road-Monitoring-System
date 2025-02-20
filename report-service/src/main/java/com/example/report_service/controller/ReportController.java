package com.example.report_service.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.report_service.dto.ReportRequest;
import com.example.report_service.dto.ReportResponse;
import com.example.report_service.dto.UploadResponse;
import com.example.report_service.model.FeedBack;
import com.example.report_service.service.ReportService;
import lombok.RequiredArgsConstructor;

//import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService  reportService;
    private final Path fileStorageLocation = Paths.get("src/main/resources/static/uploads").toAbsolutePath().normalize();

    @CrossOrigin(origins = "*")
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(new UploadResponse("No file uploaded.", null));
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = "http://localhost:8080/uploads/" + fileName;
            return ResponseEntity.ok(new UploadResponse("File uploaded successfully.", fileUrl));

        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UploadResponse("Could not upload the file: " + ex.getMessage(), null));
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReport(@RequestBody ReportRequest reportRequest) {
        reportService.createReport(reportRequest);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportResponse> getAllReports() {
        return reportService.getAllReports();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse getReportById(@PathVariable String id){
        return reportService.getReportById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateReportById(@PathVariable String id, @RequestBody ReportRequest reportRequest){
        reportService.updateReportById(id, reportRequest);
    }

    @PostMapping("/{reportId}/status")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateStatus(@PathVariable String reportId, @RequestBody Map<String, String> requestBody) {
        String statusId = requestBody.get("statusId");
        reportService.updateStatus(reportId, statusId);
    }

    @PostMapping("/{reportId}/category")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCategory(@PathVariable String reportId, @RequestBody Map<String, String> requestBody) {
        String categoryId = requestBody.get("categoryId");
        reportService.updateCategory(reportId, categoryId);
    }

    @PostMapping("/{reportId}/feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFeedback(@PathVariable String reportId, @RequestBody FeedBack feedBack) {
        reportService.createFeedback(reportId, feedBack);
    }

    @GetMapping("/{reportId}/feedback")
    @ResponseStatus(HttpStatus.OK)
    public FeedBack getFeedBack(@PathVariable String reportId) {
        return reportService.getFeedBack(reportId);
    }

    @GetMapping("/search/description")
    public ResponseEntity<List<ReportResponse>> searchReportsByDescription(@RequestParam String description) {
        return ResponseEntity.ok(reportService.searchReportsByDescription(description));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<ReportResponse>> searchReportsByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(reportService.searchReportsByCategory(categoryName));
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<ReportResponse>> searchReportsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(reportService.searchReportsByStatus(status));
    }

    @GetMapping("/search/user")
    public ResponseEntity<List<ReportResponse>> searchReportsByUser(@RequestParam String userId) {
        return ResponseEntity.ok(reportService.searchReportsByUser(userId));
    }
     

}
