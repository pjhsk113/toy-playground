package com.damda.infrastructure.oauth2.provider.google;

import com.damda.application.social.v1.GoogleOAuthLoginResponse;
import com.damda.infrastructure.oauth2.provider.ProviderProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class GoogleProfileService implements ProviderProfileService {

    private GoogleProfileApi googleProfileApi;

    @Override
    public GoogleOAuthLoginResponse getProfile(String accessToken) {
        final GoogleOAuthLoginResponse googleOAuthLoginResponse = googleProfileApi.getProfile(accessToken);
        log.info("Google Oauth UserInfo : {}", googleOAuthLoginResponse);

        return googleOAuthLoginResponse;
    }
}
