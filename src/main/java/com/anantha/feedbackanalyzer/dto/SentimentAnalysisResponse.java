package com.anantha.feedbackanalyzer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SentimentAnalysisResponse {

    private String sentiment;
    private double sentimentScore;

    private List<String> keywords;     // 🔥 THIS WAS MISSING
    private String category;

    private double confidenceLevel;
}
