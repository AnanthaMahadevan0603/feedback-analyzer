package com.anantha.feedbackanalyzer.service;

import com.anantha.feedbackanalyzer.dto.FeedbackRequest;
import com.anantha.feedbackanalyzer.dto.FeedbackResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackService {

    FeedbackResponse createFeedback(Long userId, FeedbackRequest request);

    List<FeedbackResponse> getAllFeedback(int page, int size);

    List<FeedbackResponse> getFeedbackBySentiment(String sentiment);

    List<FeedbackResponse> getFeedbackByCategory(String category);

    List<FeedbackResponse> searchByKeyword(String keyword);

    List<FeedbackResponse> getFeedbackByDateRange(LocalDateTime start, LocalDateTime end);

    List<String> getTopKeywords();

    long getTotalFeedbackCount();

    Object getDashboardAnalytics();
}
