package com.damda.application.user.domain;

import com.damda.application.social.enums.SocialLoginProvider;
import com.damda.application.social.v1.OAuthLoginResponse;
import com.damda.global.security.RoleType;
import io.jsonwebtoken.lang.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SocialLoginProvider provider;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Builder
    public User(final SocialLoginProvider provider, final String providerId, final RoleType role) {
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
    }

    public static User create(String provider, OAuthLoginResponse userInfo) {
        Assert.notNull(userInfo.getProviderId());

        return User.builder()
                .provider(SocialLoginProvider.from(provider))
                .providerId(userInfo.getProviderId())
                .role(RoleType.USER)
                .build();
    }
}

