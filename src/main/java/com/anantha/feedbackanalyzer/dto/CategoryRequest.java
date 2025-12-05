package com.anantha.feedbackanalyzer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Category name required")
    private String categoryName;

    private String description;
}
