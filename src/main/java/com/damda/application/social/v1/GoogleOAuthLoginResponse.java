package com.damda.application.social.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class GoogleOAuthLoginResponse implements OAuthLoginResponse {

    @JsonProperty("sub")
    private String providerId;
    private String name;
    private String email;
    private String picture;

    @Override
    public String getProviderId() {
        return providerId;
    }
}
