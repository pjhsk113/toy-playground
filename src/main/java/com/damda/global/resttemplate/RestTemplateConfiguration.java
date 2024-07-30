package com.damda.global.resttemplate;

import com.damda.global.properties.SocialLoginProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {
    private final SocialLoginProperties socialLoginProperties;
    private final RestTemplateBuilder templateBuilder;

    @Bean
    public RestTemplate googleProfileTemplate() {
        return templateBuilder.rootUri(socialLoginProperties.getGoogle().getProfileUrl())
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public RestTemplate naverProfileTemplate() {
        return templateBuilder.rootUri(socialLoginProperties.getNaver().getProfileUrl())
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public RestTemplate kakaoProfileTemplate() {
        return templateBuilder.rootUri(socialLoginProperties.getKakao().getProfileUrl())
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public RestTemplate appleProfileTemplate() {
        return templateBuilder.rootUri(socialLoginProperties.getApple().getKeyUrl())
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }
}
