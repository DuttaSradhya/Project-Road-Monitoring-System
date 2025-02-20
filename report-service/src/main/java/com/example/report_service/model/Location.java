package com.example.report_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "locations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Location {
    @Id
    private String id;
    @Field
    private Double latitude;
    @Field
    private Double longitude;
}
