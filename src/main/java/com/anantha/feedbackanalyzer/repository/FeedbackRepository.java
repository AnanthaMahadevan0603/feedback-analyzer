package com.anantha.feedbackanalyzer.repository;

import com.anantha.feedbackanalyzer.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // 🔍 Search feedbacks containing a specific keyword
    @Query("SELECT f FROM Feedback f JOIN f.keywords k WHERE k LIKE %:keyword%")
    List<Feedback> searchByKeyword(String keyword);

    // 🎭 Filter feedbacks by sentiment
    List<Feedback> findBySentiment(String sentiment);

    // 📂 Filter by category
    List<Feedback> findByCategory(String category);

    // 📅 Filter feedbacks between date range
    List<Feedback> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // ⭐ Top 5 most frequent keywords
    @Query(value = """
        SELECT keyword 
        FROM feedback_keywords 
        GROUP BY keyword 
        ORDER BY COUNT(keyword) DESC 
        LIMIT 5
        """, nativeQuery = true)
    List<String> getTop5Keywords();

    // 📊 Dashboard: total feedback count
    @Query("SELECT COUNT(f) FROM Feedback f")
    long getTotalFeedbackCount();

    // 📊 Dashboard: count by sentiment
    @Query("SELECT f.sentiment, COUNT(f) FROM Feedback f GROUP BY f.sentiment")
    List<Object[]> getSentimentDistribution();

    // 📊 Dashboard: count by category
    @Query("SELECT f.category, COUNT(f) FROM Feedback f GROUP BY f.category")
    List<Object[]> getCategoryDistribution();
}
