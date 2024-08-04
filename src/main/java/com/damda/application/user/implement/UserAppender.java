package com.damda.application.user.implement;

import com.damda.application.social.v1.OAuthLoginResponse;
import com.damda.application.user.domain.User;
import com.damda.application.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;

    public User append(String provider, OAuthLoginResponse userInfo) {
        return userRepository.save(User.create(provider, userInfo));
    }
}
