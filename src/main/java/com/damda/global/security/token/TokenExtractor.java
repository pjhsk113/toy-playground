package com.damda.global.security.token;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenExtractor {
    private static final String HEADER_PREFIX = "Bearer ";

    public static String extract(String header) {
        if (header == null || header.isBlank()) {
            return null;
        }

        if (header.length() < HEADER_PREFIX.length()) {
            return null;
        }

        return header.substring(HEADER_PREFIX.length());
    }
}
