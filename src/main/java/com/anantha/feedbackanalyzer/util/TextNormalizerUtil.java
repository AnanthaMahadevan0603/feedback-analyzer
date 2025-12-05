package com.anantha.feedbackanalyzer.util;

public class TextNormalizerUtil {

    public static String normalize(String text) {
        if (text == null) return "";

        return text.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")  // remove punctuation
                .trim()
                .replaceAll("\\s+", " ");        // normalize spaces
    }
}
