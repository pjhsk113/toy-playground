package com.damda.application.social.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaverOAuthLoginResponse implements OAuthLoginResponse {

    @JsonProperty("resultcode")
    private String resultCode;
    private String message;
    @JsonProperty("response")
    private Response userInfo;

    @Getter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        @JsonProperty("id")
        private String providerId;
        @JsonProperty("nickname")
        private String nickName;
        private String name;
        private String email;
        private String gender;
        private String age;
        @JsonProperty("birthyear")
        private String birthYear;
        private String birthday;
        private String mobile;
        @JsonProperty("profile_image")
        private String profileImage;
    }

    @Override
    public String getProviderId() {
        return userInfo.getProviderId();
    }
}
