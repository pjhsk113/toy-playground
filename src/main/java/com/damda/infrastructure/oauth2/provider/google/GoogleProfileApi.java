package com.damda.infrastructure.oauth2.provider.google;

import com.damda.application.social.v1.GoogleOAuthLoginResponse;
import com.damda.global.properties.SocialLoginProperties;
import com.damda.infrastructure.oauth2.provider.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GoogleProfileApi {
    private final SocialLoginProperties socialLoginProperties;
    @Qualifier("googleProfileTemplate")
    private final RestTemplate googleProfileTemplate;

    public GoogleOAuthLoginResponse getProfile(String accessToken) {
        HttpEntity<MultiValueMap<String, String>> request = HeaderUtils.createAccessTokenHeader(accessToken);
        return googleProfileTemplate.postForObject(socialLoginProperties.getGoogle().getUri(), request, GoogleOAuthLoginResponse.class);
    }
}
