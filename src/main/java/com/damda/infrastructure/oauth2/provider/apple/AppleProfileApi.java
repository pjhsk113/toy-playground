package com.damda.infrastructure.oauth2.provider.apple;

import com.damda.global.properties.SocialLoginProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AppleProfileApi {
    private final SocialLoginProperties socialLoginProperties;
    @Qualifier("appleProfileTemplate")
    private final RestTemplate appleProfileTemplate;

    public ApplePublicKeyResponse getPublicKey() {
        return appleProfileTemplate.getForObject(socialLoginProperties.getApple().getUri(), ApplePublicKeyResponse.class);
    }
}
