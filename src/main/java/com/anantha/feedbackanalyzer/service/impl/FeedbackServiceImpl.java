package com.anantha.feedbackanalyzer.service.impl;

import com.anantha.feedbackanalyzer.dto.FeedbackRequest;
import com.anantha.feedbackanalyzer.dto.FeedbackResponse;
import com.anantha.feedbackanalyzer.entity.*;
import com.anantha.feedbackanalyzer.exception.ResourceNotFoundException;
import com.anantha.feedbackanalyzer.repository.*;
import com.anantha.feedbackanalyzer.service.AIService;
import com.anantha.feedbackanalyzer.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AIService aiService;

    @Autowired
    private AIResultRepository aiResultRepository;

    @Autowired
    private FeedbackCategoryRepository categoryRepository;

    // ✅ helper – safely map entity → DTO even if aiResult = null
    private FeedbackResponse mapToResponse(Feedback f) {
        AIResult ai = f.getAiResult();
        double sentimentScore = ai != null ? ai.getSentimentScore() : 0.0;
        double confidence = ai != null ? ai.getConfidenceLevel() : 0.0;

        return FeedbackResponse.builder()
                .id(f.getId())
                .userId(f.getUser().getId())
                .message(f.getMessage())
                .rating(f.getRating())
                .sentiment(f.getSentiment())
                .keywords(f.getKeywords())
                .category(f.getCategory())
                .sentimentScore(sentimentScore)
                .confidenceLevel(confidence)
                .createdAt(f.getCreatedAt())
                .build();
    }

    @Override
    public FeedbackResponse createFeedback(Long userId, FeedbackRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // AI Processing
        String sentiment = aiService.analyzeSentiment(request.getMessage());
        double score = aiService.computeSentimentScore(request.getMessage());
        List<String> keywords = aiService.extractKeywords(request.getMessage());

        List<String> availableCategories = categoryRepository.findAll()
                .stream().map(FeedbackCategory::getCategoryName).toList();

        String category = aiService.detectCategory(request.getMessage(), availableCategories);
        double confidence = aiService.computeConfidenceScore(request.getMessage());

        // Build Feedback
        Feedback feedback = Feedback.builder()
                .user(user)
                .message(request.getMessage())
                .rating(request.getRating())
                .sentiment(sentiment)
                .keywords(new ArrayList<>(keywords))
                .category(category)
                .build();

        // Build AIResult and link both sides
        AIResult result = AIResult.builder()
                .sentimentScore(score)
                .extractedKeywords(new ArrayList<>(keywords))
                .confidenceLevel(confidence)
                .feedback(feedback)
                .build();

        feedback.setAiResult(result);

        // Because Feedback.aiResult has cascade = ALL, one save is enough
        feedbackRepository.save(feedback);
        // (aiResultRepository.save(result);  // not needed, but OK if you keep it)

        return mapToResponse(feedback);
    }

    @Override
    public List<FeedbackResponse> getAllFeedback(int page, int size) {
        return feedbackRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<FeedbackResponse> getFeedbackBySentiment(String sentiment) {
        return feedbackRepository.findBySentiment(sentiment)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<FeedbackResponse> getFeedbackByCategory(String category) {
        return feedbackRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<FeedbackResponse> searchByKeyword(String keyword) {
        return feedbackRepository.searchByKeyword(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<FeedbackResponse> getFeedbackByDateRange(LocalDateTime start, LocalDateTime end) {
        return feedbackRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<String> getTopKeywords() {
        return feedbackRepository.getTop5Keywords();
    }

    @Override
    public long getTotalFeedbackCount() {
        return feedbackRepository.getTotalFeedbackCount();
    }

    @Override
    public Object getDashboardAnalytics() {
        return feedbackRepository.getSentimentDistribution();
    }
}
