package com.example.report_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReportRequest {
    private String userId;
    private String photoUrl;
    private String description;
    private String categoryId;
    private Double latitude;
    private Double longitude;
    private String status;
}