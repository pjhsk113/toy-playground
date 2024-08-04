package com.damda.application.user.implement;

import com.damda.application.social.v1.OAuthLoginResponse;
import com.damda.infrastructure.oauth2.provider.OAuthProviderFactory;
import com.damda.infrastructure.oauth2.provider.ProviderProfileService;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthProvider {
    private final OAuthProviderFactory oAuthProviderFactory;

    public OAuthLoginResponse getProfileFromProvider(String accessToken, String provider) {
        final ProviderProfileService profileProviderService = oAuthProviderFactory.getProvider(provider);
        final OAuthLoginResponse userInfo = profileProviderService.getProfile(accessToken);

        validateUserProfile(userInfo);

        return userInfo;
    }

    private void validateUserProfile(OAuthLoginResponse userInfo) {
        Assert.notNull(userInfo.getProviderId(), "providerId has null");
    }
}
