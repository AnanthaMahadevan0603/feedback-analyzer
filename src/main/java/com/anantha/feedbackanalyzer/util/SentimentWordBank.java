package com.anantha.feedbackanalyzer.util;

import java.util.Arrays;
import java.util.List;

public class SentimentWordBank {

    public static final List<String> POSITIVE_WORDS = Arrays.asList(
            "good", "great", "excellent", "amazing", "superb", "satisfied",
            "happy", "love", "best", "positive", "enjoyed", "fantastic"
    );

    public static final List<String> NEGATIVE_WORDS = Arrays.asList(
            "bad", "poor", "terrible", "worst", "sad", "angry", "hate",
            "unsatisfied", "disappointed", "negative", "frustrated"
    );
}
