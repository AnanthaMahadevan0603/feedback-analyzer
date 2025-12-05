package com.anantha.feedbackanalyzer.controller;

import com.anantha.feedbackanalyzer.dto.SentimentAnalysisResponse;
import com.anantha.feedbackanalyzer.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/analyze")
    public SentimentAnalysisResponse analyze(@RequestBody String message) {

        return SentimentAnalysisResponse.builder()
                .sentiment(aiService.analyzeSentiment(message))
                .keywords(aiService.extractKeywords(message))
                .category(aiService.detectCategory(message, java.util.List.of("Service", "Product", "Support")))
                .sentimentScore(aiService.computeSentimentScore(message))
                .confidenceLevel(aiService.computeConfidenceScore(message))
                .build();
    }
}
