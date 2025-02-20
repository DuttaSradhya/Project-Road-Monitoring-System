package com.example.report_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "statuses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportStatus {

    @Id
    private String id;
    @Field
    private String status;
}
