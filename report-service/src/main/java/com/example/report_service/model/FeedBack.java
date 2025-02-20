package com.example.report_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FeedBack {
    @Id
    private String id;
    @Field
    private String userId;
    @Field
    private String comment;
    @Field
    private int rating;

}
