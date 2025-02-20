package com.example.analysis_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedBack {
    private String id;
    private String userId;
    private String comment;
    private int rating;
}
