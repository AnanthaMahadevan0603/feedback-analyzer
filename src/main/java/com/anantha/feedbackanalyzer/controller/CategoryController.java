package com.anantha.feedbackanalyzer.controller;

import com.anantha.feedbackanalyzer.dto.CategoryRequest;
import com.anantha.feedbackanalyzer.entity.FeedbackCategory;
import com.anantha.feedbackanalyzer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public FeedbackCategory create(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("")
    public List<FeedbackCategory> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{name}")
    public FeedbackCategory getByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
