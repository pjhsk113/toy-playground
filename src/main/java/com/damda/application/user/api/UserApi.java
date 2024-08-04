package com.damda.application.user.api;


import com.damda.application.user.api.v1.LoginRequest;
import com.damda.application.user.api.v1.UserLoginTokenResponse;
import com.damda.application.user.domain.User;
import com.damda.application.user.service.UserService;
import com.damda.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    // TODO - 테스용 API입니다.
    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public User getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userService.find(userPrincipal.getId());
    }

    // TODO - 소셜 로그인 테스용 API
    @PostMapping("/{provider}")
    public ResponseEntity<UserLoginTokenResponse> socialLogin(@PathVariable String provider, @RequestBody LoginRequest request) {
        String jwtToken = userService.socialLogin(request.getAccessToken(), provider);

        return ResponseEntity.ok(
                new UserLoginTokenResponse(jwtToken)
        );
    }

    // TODO - 테스용 API
    @GetMapping("/dummy")
    public String dummy() {
        return "stupid";
    }
}
