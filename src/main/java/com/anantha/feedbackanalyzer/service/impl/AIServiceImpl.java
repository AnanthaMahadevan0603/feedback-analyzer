package com.anantha.feedbackanalyzer.service.impl;

import com.anantha.feedbackanalyzer.service.AIService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AIServiceImpl implements AIService {

    private static final Set<String> POSITIVE_WORDS = Set.of(
            "good", "excellent", "awesome", "fast", "clean", "happy", "love", "satisfied", "great"
    );

    private static final Set<String> NEGATIVE_WORDS = Set.of(
            "bad", "slow", "poor", "hate", "terrible", "angry", "worst", "disappointed"
    );

    private static final Set<String> STOPWORDS = Set.of(
            "the", "is", "are", "a", "an", "to", "and", "in", "on", "of", "that", "this", "was"
    );

    @Override
    public String analyzeSentiment(String text) {

        String normalized = text.toLowerCase();
        int positive = 0;
        int negative = 0;

        for (String word : normalized.split("\\s+")) {
            if (POSITIVE_WORDS.contains(word)) positive++;
            if (NEGATIVE_WORDS.contains(word)) negative++;
        }

        if (positive > negative) return "POSITIVE";
        if (negative > positive) return "NEGATIVE";
        return "NEUTRAL";
    }

    @Override
    public List<String> extractKeywords(String text) {

        List<String> keywords = new ArrayList<>();

        for (String w : text.toLowerCase().split("\\s+")) {
            if (!STOPWORDS.contains(w) && w.length() > 3) {
                keywords.add(w.replaceAll("[^a-z]", ""));
            }
        }

        // return MUTABLE list
        return new ArrayList<>(keywords);
    }


    @Override
    public String detectCategory(String text, List<String> predefinedCategories) {

        List<String> words = extractKeywords(text);

        Map<String, Integer> scores = new HashMap<>();

        for (String category : predefinedCategories) {
            int score = 0;
            for (String word : words) {
                if (category.toLowerCase().contains(word)) score++;
            }
            scores.put(category, score);
        }

        return scores.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("General");
    }

    @Override
    public double computeConfidenceScore(String text) {
        double lengthScore = Math.min(1.0, text.length() / 200.0);
        double keywordScore = extractKeywords(text).size() / 10.0;
        double sentimentScore = computeSentimentScore(text);

        return (lengthScore + keywordScore + sentimentScore) / 3.0;
    }

    @Override
    public double computeSentimentScore(String text) {

        String normalized = text.toLowerCase();

        int positive = 0, negative = 0;

        for (String word : normalized.split("\\s+")) {
            if (POSITIVE_WORDS.contains(word)) positive++;
            if (NEGATIVE_WORDS.contains(word)) negative++;
        }

        int total = positive + negative;
        if (total == 0) return 0.5;

        return (double) positive / total;
    }
}
