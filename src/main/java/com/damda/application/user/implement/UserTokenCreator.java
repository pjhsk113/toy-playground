package com.damda.application.user.implement;

import com.damda.application.user.domain.User;
import com.damda.global.properties.AppProperties;
import com.damda.global.security.RoleType;
import com.damda.global.security.token.JwtToken;
import com.damda.global.security.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserTokenCreator {
    private final JwtTokenProvider tokenProvider;
    private final AppProperties appProperties;

    public String createJwtToken(User user) {
        LocalDateTime currentTime = LocalDateTime.now();
        Date expiryDate = Date.from(currentTime.plusMinutes(appProperties.getAuth().getTokenExpirationMsec()).atZone(ZoneId.systemDefault()).toInstant());

        JwtToken accessToken = tokenProvider.createAuthToken(
                user.getId(),
                RoleType.USER.getCode(),
                expiryDate
        );

        return accessToken.getToken();
    }
}
