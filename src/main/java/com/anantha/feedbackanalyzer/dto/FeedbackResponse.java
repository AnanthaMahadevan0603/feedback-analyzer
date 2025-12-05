package com.anantha.feedbackanalyzer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class FeedbackResponse {

    private Long id;
    private Long userId;
    private String message;
    private Integer rating;

    private String sentiment;
    private List<String> keywords;
    private String category;

    private double sentimentScore;
    private double confidenceLevel;

    private LocalDateTime createdAt;
}
