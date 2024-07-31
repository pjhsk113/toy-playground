package com.damda.application.social.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class AppleOAuthLoginResponse implements OAuthLoginResponse {
    @JsonProperty("sub")
    private String providerId;

    public static AppleOAuthLoginResponse from(Claims claims) {
        return new AppleOAuthLoginResponse(
                claims.getSubject()
        );
    }

    @Override
    public String getProviderId() {
        return providerId;
    }
}
