package com.anantha.feedbackanalyzer.repository;

import com.anantha.feedbackanalyzer.entity.FeedbackCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackCategoryRepository extends JpaRepository<FeedbackCategory, Long> {

    Optional<FeedbackCategory> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);
}
