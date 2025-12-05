package com.anantha.feedbackanalyzer.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ai_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    private double sentimentScore;

    @ElementCollection
    @CollectionTable(name = "ai_keywords", joinColumns = @JoinColumn(name = "ai_result_id"))
    @Column(name = "keyword")
    private List<String> extractedKeywords;

    private double confidenceLevel;
}
