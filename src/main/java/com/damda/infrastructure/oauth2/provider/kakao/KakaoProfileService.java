package com.damda.infrastructure.oauth2.provider.kakao;

import com.damda.application.social.v1.KakaoOAuthLoginResponse;
import com.damda.infrastructure.oauth2.provider.ProviderProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KakaoProfileService implements ProviderProfileService {

    private KakaoProfileApi kakaoProfileApi;

    @Override
    public KakaoOAuthLoginResponse getProfile(String accessToken) {
        final KakaoOAuthLoginResponse kakaoOAuthLoginResponse = kakaoProfileApi.getProfile(accessToken);
        log.info("Kakao Oauth UserInfo : {}", kakaoOAuthLoginResponse);

        return kakaoOAuthLoginResponse;
    }
}
