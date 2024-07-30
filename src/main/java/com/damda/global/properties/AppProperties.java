package com.damda.global.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
@ConstructorBinding
public class AppProperties {
    private final Auth auth;
    private final OAuth2 oauth2;

    @Getter
    @RequiredArgsConstructor
    public static class Auth {
        private final String tokenSecret;
        private final long tokenExpirationMsec;
    }

    @Getter
    @RequiredArgsConstructor
    public static final class OAuth2 {
        private final List<String> authorizedRedirectUris;
    }
}
