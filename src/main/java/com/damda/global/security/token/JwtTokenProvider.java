package com.damda.global.security.token;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtTokenProvider {

    private final Key key;

    public JwtTokenProvider(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public JwtToken createAuthToken(Long id, String role, Date expiry) {
        return new JwtToken(id, role, expiry, key);
    }

    public JwtToken convertAuthToken(String token) {
        return new JwtToken(token, key);
    }
}
