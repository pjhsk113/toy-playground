package com.damda.infrastructure.oauth2.provider;

import com.damda.application.social.enums.SocialLoginProvider;
import com.damda.infrastructure.oauth2.provider.apple.AppleProfileService;
import com.damda.infrastructure.oauth2.provider.google.GoogleProfileService;
import com.damda.infrastructure.oauth2.provider.kakao.KakaoProfileService;
import com.damda.infrastructure.oauth2.provider.naver.NaverProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OAuthProviderFactory {
    private final GoogleProfileService googleProfileService;
    private final NaverProfileService naverProfileService;
    private final KakaoProfileService kakaoProfileService;
    private final AppleProfileService appleProfileService;

    public ProviderProfileService getProvider(String provider) {
        SocialLoginProvider socialLoginProvider = SocialLoginProvider.from(provider);

        switch (socialLoginProvider) {
            case GOOGLE:
                return googleProfileService;
            case NAVER:
                return naverProfileService;
            case KAKAO:
                return kakaoProfileService;
            case APPLE:
                return appleProfileService;
            default:
                throw new IllegalArgumentException();
        }
    }
}
