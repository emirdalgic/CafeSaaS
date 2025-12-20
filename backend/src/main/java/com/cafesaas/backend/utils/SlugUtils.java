package com.cafesaas.backend.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");

        String noTurkish = input
                .replace("ı", "i").replace("İ", "i")
                .replace("ğ", "g").replace("Ğ", "g")
                .replace("ü", "u").replace("Ü", "u")
                .replace("ş", "s").replace("Ş", "s")
                .replace("ö", "o").replace("Ö", "o")
                .replace("ç", "c").replace("Ç", "c");

        String noWhitespace = WHITESPACE.matcher(noTurkish).replaceAll("-");

        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);

        String slug = NONLATIN.matcher(normalized).replaceAll("");

        return slug.toLowerCase(Locale.ENGLISH);
    }
}
