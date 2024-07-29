package com.damda.global.security;

import com.damda.application.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private Long id;
    private RoleType roleType;
    private Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(Long id, RoleType role, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.roleType = role;
        this.authorities = authorities;
    }

    public static UserPrincipal from(User user) {
        return new UserPrincipal(
                user.getId(),
                user.getRole(),
                createAuthorities()
        );
    }

    private static List<GrantedAuthority> createAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getCode()));
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return null;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
