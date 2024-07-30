package com.damda.global.config;

import com.damda.global.security.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtTokenProvider authTokenProvider() {
        return new JwtTokenProvider(secret);
    }
}
