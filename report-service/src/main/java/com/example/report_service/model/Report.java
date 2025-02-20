package com.example.report_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "reports")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Report {

    @Id
    private String id;
    @Field
    private String userId;
    @Field
    private String photoUrl;
    @Field
    private String description;
    @DBRef
    private Location location;
    @DBRef
    private Category category;
    @DBRef
    private ReportStatus status;  
    @DBRef
    private FeedBack feedBack; 
}
