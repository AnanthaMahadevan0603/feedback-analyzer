package com.anantha.feedbackanalyzer.controller;

import com.anantha.feedbackanalyzer.dto.CategoryRequest;
import com.anantha.feedbackanalyzer.entity.FeedbackCategory;
import com.anantha.feedbackanalyzer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class FeedbackCategoryController {

    @Autowired
    private CategoryService categoryService;

    // CREATE CATEGORY
    @PostMapping
    public FeedbackCategory createCategory(@RequestBody CategoryRequest category) {
        return categoryService.createCategory(category);
    }

    // GET ALL CATEGORIES
    @GetMapping
    public List<FeedbackCategory> getAll() {
        return categoryService.getAllCategories();
    }
}
