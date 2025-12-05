package com.anantha.feedbackanalyzer.util;

import java.util.*;
import java.util.stream.Collectors;

public class KeywordExtractorUtil {

    public static List<String> extractKeywords(String text) {

        if (text == null || text.isEmpty()) return Collections.emptyList();

        String normalized = TextNormalizerUtil.normalize(text);

        String[] words = normalized.split(" ");

        return Arrays.stream(words)
                .filter(word -> word.length() > 2)
                .filter(word -> !StopwordListUtil.STOPWORDS.contains(word))
                .distinct()
                .collect(Collectors.toList());
    }
}
