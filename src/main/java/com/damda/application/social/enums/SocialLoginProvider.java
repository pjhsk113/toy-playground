package com.damda.application.social.enums;

import com.damda.application.user.exception.NotSupportedProviderException;

import java.util.Arrays;

public enum SocialLoginProvider {
    GOOGLE,
    NAVER,
    KAKAO,
    APPLE,
    LOCAL;

    public static SocialLoginProvider from(String provider) {
        return Arrays.stream(values())
                .filter(socialLoginProvider -> socialLoginProvider.name().equals(provider.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new NotSupportedProviderException("this provider not supported yet"));
    }
}
