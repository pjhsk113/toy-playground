package com.damda.infrastructure.oauth2.provider.naver;

import com.damda.application.social.v1.NaverOAuthLoginResponse;
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
public class NaverProfileApi {
    private final SocialLoginProperties socialLoginProperties;
    @Qualifier("naverProfileTemplate")
    private final RestTemplate naverProfileTemplate;

    public NaverOAuthLoginResponse getProfile(String accessToken) {
        HttpEntity<MultiValueMap<String, String>> request = HeaderUtils.createAccessTokenHeader(accessToken);
        return naverProfileTemplate.postForObject(socialLoginProperties.getNaver().getUri(), request, NaverOAuthLoginResponse.class);
    }
}
