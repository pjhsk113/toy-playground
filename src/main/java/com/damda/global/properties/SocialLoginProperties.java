package com.damda.global.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "social-login.provider")
@ConstructorBinding
public class SocialLoginProperties {
    private final Google google;
    private final Naver naver;
    private final Kakao kakao;
    private final Apple apple;

    @Getter
    @RequiredArgsConstructor
    public static class Google {
        private final String profileUrl;
        private final String uri;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Naver {
        private final String profileUrl;
        private final String uri;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Kakao {
        private final String profileUrl;
        private final String uri;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Apple {
        private final String keyUrl;
        private final String uri;
    }
}
