package com.damda.application.user.service;

import com.damda.application.social.v1.OAuthLoginResponse;
import com.damda.application.user.domain.User;
import com.damda.application.user.implement.OAuthProvider;
import com.damda.application.user.implement.UserAppender;
import com.damda.application.user.implement.UserReader;
import com.damda.application.user.implement.UserTokenCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final OAuthProvider oAuthProvider;
    private final UserTokenCreator userTokenCreator;
    private final UserAppender userAppender;
    private final UserReader userReader;

    public User find(Long userId) {
        return userReader.read(userId);
    }

    public String socialLogin(String accessToken, String provider) {
        final OAuthLoginResponse userInfo = oAuthProvider.getProfileFromProvider(accessToken, provider);

        User user = userReader.readByProviderId(userInfo);

        if (isExist(user)) {
            return login(user);
        }

        return login(userAppender.append(provider, userInfo));
    }

    private String login(User user) {
        return userTokenCreator.createJwtToken(user);
    }

    private boolean isExist(final User user) {
        return user.getProviderId() != null;
    }
}
