package com.anantha.feedbackanalyzer.service.impl;

import com.anantha.feedbackanalyzer.dto.CategoryRequest;
import com.anantha.feedbackanalyzer.entity.FeedbackCategory;
import com.anantha.feedbackanalyzer.exception.BadRequestException;
import com.anantha.feedbackanalyzer.exception.ResourceNotFoundException;
import com.anantha.feedbackanalyzer.repository.FeedbackCategoryRepository;
import com.anantha.feedbackanalyzer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private FeedbackCategoryRepository categoryRepository;

    @Override
    public FeedbackCategory createCategory(CategoryRequest request) {

        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new BadRequestException("Category already exists");
        }

        FeedbackCategory category = FeedbackCategory.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public List<FeedbackCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public FeedbackCategory getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}
