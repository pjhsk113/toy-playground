package com.damda.infrastructure.oauth2.provider.naver;

import com.damda.application.social.v1.NaverOAuthLoginResponse;
import com.damda.infrastructure.oauth2.provider.ProviderProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NaverProfileService implements ProviderProfileService {

    private NaverProfileApi naverProfileApi;

    @Override
    public NaverOAuthLoginResponse getProfile(String accessToken) {
        final NaverOAuthLoginResponse naverOAuthLoginResponse = naverProfileApi.getProfile(accessToken);
        log.info("Naver Oauth UserInfo : {}", naverOAuthLoginResponse);

        return naverOAuthLoginResponse;
    }
}
