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
public class KakaoOAuthLoginResponse implements OAuthLoginResponse {

    @JsonProperty("id")
    private String providerId;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class KakaoAccount {
        private String email;

        @Getter
        @NoArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Profile {
            @JsonProperty("nickname")
            private String nickName;
            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;
        }
    }

    @Override
    public String getProviderId() {
        return providerId;
    }
}
