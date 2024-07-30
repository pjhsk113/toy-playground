package com.damda.infrastructure.oauth2.provider;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public class HeaderUtils {
    private static final String DELIMITER = ".";

    private HeaderUtils() { }

    public static HttpEntity<MultiValueMap<String, String>> createAccessTokenHeader(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        return new HttpEntity<>(null, httpHeaders);
    }

    public static String extractTokenHeader(String accessToken) {
        return accessToken.substring(0, accessToken.indexOf(DELIMITER));
    }
}
