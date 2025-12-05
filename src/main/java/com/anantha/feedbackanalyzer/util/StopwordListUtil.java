package com.anantha.feedbackanalyzer.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StopwordListUtil {

    public static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
            "the", "is", "am", "are", "was", "were", "a", "an", "and", "or",
            "of", "to", "for", "with", "on", "in", "at", "by", "from", "this",
            "that", "it", "as", "be", "have", "has", "had", "but", "so", "if",
            "into", "about", "not", "your", "my", "we", "they", "you", "their"
    ));
}
