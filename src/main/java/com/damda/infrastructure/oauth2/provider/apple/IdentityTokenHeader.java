package com.damda.infrastructure.oauth2.provider.apple;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class IdentityTokenHeader {

    private String kid;
    private String alg;

}
