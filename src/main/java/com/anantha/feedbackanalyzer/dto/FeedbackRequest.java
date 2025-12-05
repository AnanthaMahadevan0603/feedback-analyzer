package com.anantha.feedbackanalyzer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FeedbackRequest {

    @NotBlank(message = "Message cannot be empty")
    private String message;

    private Integer rating;
}
