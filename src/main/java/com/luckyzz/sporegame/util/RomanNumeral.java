package com.luckyzz.sporegame.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeral {

    private static final Map<String, Integer> ROMAN = new LinkedHashMap<>();

    static {
        ROMAN.put("M", 1000);
        ROMAN.put("CM", 900);
        ROMAN.put("D", 500);
        ROMAN.put("CD", 400);
        ROMAN.put("C", 100);
        ROMAN.put("XC", 90);
        ROMAN.put("L", 50);
        ROMAN.put("XL", 40);
        ROMAN.put("X", 10);
        ROMAN.put("IX", 9);
        ROMAN.put("V", 5);
        ROMAN.put("IV", 4);
        ROMAN.put("I", 1);
    }

    private RomanNumeral() {
        throw new UnsupportedOperationException();
    }

    public static @NotNull String toRoman(int value) {
        final StringBuilder builder = new StringBuilder();
        for(final Map.Entry<String, Integer> entry : ROMAN.entrySet()){
            final int matches = value / entry.getValue();
            builder.append(repeat(entry.getKey(), matches));
            value = value % entry.getValue();
        }
        return builder.toString();
    }

    private static @Nullable String repeat(@Nullable final String input, final int n) {
        if(input == null) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < n; i++) {
            builder.append(input);
        }
        return builder.toString();
    }

}
