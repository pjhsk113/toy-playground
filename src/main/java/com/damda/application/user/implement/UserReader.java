package com.damda.application.user.implement;

import com.damda.application.social.v1.OAuthLoginResponse;
import com.damda.application.user.domain.User;
import com.damda.application.user.domain.UserRepository;
import com.damda.application.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;

    public User read(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
    public User readByProviderId(OAuthLoginResponse userInfo) {
        return userRepository.findByProviderId(userInfo.getProviderId())
                .orElseGet(() -> User.builder().build());
    }
}
