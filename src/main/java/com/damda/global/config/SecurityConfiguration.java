package com.damda.global.config;

import com.damda.global.security.CustomUserDetailsService;
import com.damda.global.security.RestAuthenticationEntryPoint;
import com.damda.global.security.RoleType;
import com.damda.global.security.token.JwtTokenFilter;
import com.damda.global.security.token.JwtTokenProvider;
import com.damda.global.security.token.TokenAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * oAuth2 의존성 제거 예정
 */
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()

                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()

                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()

                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
                .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
                .antMatchers("/auth/**", "/oauth2/**").permitAll()
                .antMatchers("/users/google", "/users/naver", "/users/kakao", "/users/apple").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * JWT 토큰 필터 등록
     * @return
     */
    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(tokenProvider, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
