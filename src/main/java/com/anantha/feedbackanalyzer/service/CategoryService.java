package com.anantha.feedbackanalyzer.service;

import com.anantha.feedbackanalyzer.dto.CategoryRequest;
import com.anantha.feedbackanalyzer.entity.FeedbackCategory;

import java.util.List;

public interface CategoryService {

    FeedbackCategory createCategory(CategoryRequest request);

    List<FeedbackCategory> getAllCategories();

    FeedbackCategory getCategoryByName(String name);
}
