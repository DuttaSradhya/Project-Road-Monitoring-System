package com.example.analysis_service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {

    private String id;
    private String userId;
    private String photoUrl;
    private String description;
    private Location location;
    private Category category;
    private ReportStatus status;
    private FeedBack feedback;
}
