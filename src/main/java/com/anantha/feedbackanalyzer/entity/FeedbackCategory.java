package com.anantha.feedbackanalyzer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "feedback_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name required")
    @Column(unique = true, nullable = false)
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "feedbackCategory")
    private List<Feedback> feedbackList;
}
