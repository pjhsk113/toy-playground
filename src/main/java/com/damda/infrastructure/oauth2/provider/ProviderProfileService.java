package com.damda.infrastructure.oauth2.provider;

import com.damda.application.social.v1.OAuthLoginResponse;

public interface ProviderProfileService {
    OAuthLoginResponse getProfile(String accessToken);
}
