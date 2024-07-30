package com.damda.infrastructure.oauth2.provider.kakao;

import com.damda.application.social.v1.KakaoOAuthLoginResponse;
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
public class KakaoProfileApi {
    private final SocialLoginProperties socialLoginProperties;
    @Qualifier("kakaoProfileTemplate")
    private final RestTemplate kakaoProfileTemplate;

    public KakaoOAuthLoginResponse getProfile(String accessToken) {
        HttpEntity<MultiValueMap<String, String>> request = HeaderUtils.createAccessTokenHeader(accessToken);
        return kakaoProfileTemplate.postForObject(socialLoginProperties.getKakao().getUri(), request, KakaoOAuthLoginResponse.class);
    }
}
