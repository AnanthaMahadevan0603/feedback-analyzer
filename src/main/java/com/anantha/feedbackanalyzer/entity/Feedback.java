package com.anantha.feedbackanalyzer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @NotBlank(message = "Message cannot be empty")
    private String message;

    private Integer rating;

    @Column(nullable = false)
    private String sentiment;

    @ElementCollection
    @CollectionTable(name = "feedback_keywords", joinColumns = @JoinColumn(name = "feedback_id"))
    @Column(name = "keyword")
    private List<String> keywords;

    private String category;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "feedback", cascade = CascadeType.ALL)
    private AIResult aiResult;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FeedbackCategory feedbackCategory;
}
