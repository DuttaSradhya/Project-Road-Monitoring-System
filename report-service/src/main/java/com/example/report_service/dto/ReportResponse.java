package com.example.report_service.dto;

import com.example.report_service.model.Category;
import com.example.report_service.model.FeedBack;
import com.example.report_service.model.Location;
import com.example.report_service.model.ReportStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private String id;
    private String userId;
    private String photoUrl;
    private String description;
    private Location location;
    private Category category;
    private ReportStatus status;
    private FeedBack feedBack;
    // private String password;
}
