package com.damda.infrastructure.oauth2.provider.apple;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplePublicKeyResponse {

    private List<Key> keys;

    @Getter
    public static class Key {
        private String kty;
        private String kid;
        private String use;
        private String alg;
        private String n;
        private String e;
    }

    public Key getMatchKey(IdentityTokenHeader header) {
        return keys.stream()
                .filter(key -> isMatched(key, header))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Failed get public key from apple's id server"));
    }

    private boolean isMatched(Key key, IdentityTokenHeader header) {
        return key.getKid().equals(header.getKid()) && key.getAlg().equals(header.getAlg());
    }
}
