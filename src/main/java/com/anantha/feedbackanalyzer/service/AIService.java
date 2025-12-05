package com.anantha.feedbackanalyzer.service;

import java.util.List;

public interface AIService {

    // 1️⃣ Analyze sentiment → POSITIVE / NEGATIVE / NEUTRAL
    String analyzeSentiment(String text);

    // 2️⃣ Extract important keywords from text
    List<String> extractKeywords(String text);

    // 3️⃣ Detect best-fit category based on extracted keywords
    String detectCategory(String text, List<String> predefinedCategories);

    // 4️⃣ Compute confidence score (0.0 - 1.0)
    double computeConfidenceScore(String text);

    // 5️⃣ Sentiment Score (0–1 scale)
    double computeSentimentScore(String text);
}
