package com.anantha.feedbackanalyzer.controller;

import com.anantha.feedbackanalyzer.dto.FeedbackRequest;
import com.anantha.feedbackanalyzer.dto.FeedbackResponse;
import com.anantha.feedbackanalyzer.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/create/{userId}")
    public FeedbackResponse create(
            @PathVariable Long userId,
            @RequestBody FeedbackRequest request
    ) {
        return feedbackService.createFeedback(userId, request);
    }

    @GetMapping("")
    public List<FeedbackResponse> all() {
        return feedbackService.getAllFeedback(0, 20);
    }

    @GetMapping("/sentiment/{type}")
    public List<FeedbackResponse> bySentiment(@PathVariable String type) {
        return feedbackService.getFeedbackBySentiment(type);
    }

    @GetMapping("/category/{cat}")
    public List<FeedbackResponse> byCategory(@PathVariable String cat) {
        return feedbackService.getFeedbackByCategory(cat);
    }

    @GetMapping("/search/{keyword}")
    public List<FeedbackResponse> search(@PathVariable String keyword) {
        return feedbackService.searchByKeyword(keyword);
    }

    @GetMapping("/date-range")
    public List<FeedbackResponse> dateRange(
            @RequestParam String start,
            @RequestParam String end) {

        LocalDateTime s = LocalDateTime.parse(start);
        LocalDateTime e = LocalDateTime.parse(end);

        return feedbackService.getFeedbackByDateRange(s, e);
    }

    @GetMapping("/top-keywords")
    public List<String> topKeywords() {
        return feedbackService.getTopKeywords();
    }

    @GetMapping("/analytics")
    public Object analytics() {
        return feedbackService.getDashboardAnalytics();
    }
}
