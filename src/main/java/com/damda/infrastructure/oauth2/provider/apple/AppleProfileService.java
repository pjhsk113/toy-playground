package com.damda.infrastructure.oauth2.provider.apple;

import com.damda.application.social.v1.AppleOAuthLoginResponse;
import com.damda.infrastructure.oauth2.provider.HeaderUtils;
import com.damda.infrastructure.oauth2.provider.ProviderProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Slf4j
@Service
@AllArgsConstructor
public class AppleProfileService implements ProviderProfileService {
    private AppleProfileApi appleProfileApi;

    @Override
    public AppleOAuthLoginResponse getProfile(String accessToken) {
        final ApplePublicKeyResponse applePublicKeys = appleProfileApi.getPublicKey();
        IdentityTokenHeader header = decodeHeader(accessToken);
        ApplePublicKeyResponse.Key key = applePublicKeys.getMatchKey(header);
        PublicKey publicKey = createPublicKey(key);

        return AppleOAuthLoginResponse.from(parseClaims(publicKey, accessToken));
    }

    // 전달받은 토큰 헤더 복호화
    private IdentityTokenHeader decodeHeader(String token) {
        String identityTokenHeader = HeaderUtils.extractTokenHeader(token);
        try {
            return new ObjectMapper().readValue(decode(identityTokenHeader), IdentityTokenHeader.class);
        } catch (Exception e) {
            throw new IllegalStateException("decoding failed");
        }
    }

    private String decode(String encodingString) {
        return new String(Base64.getDecoder().decode(encodingString.getBytes(StandardCharsets.UTF_8)));
    }

    // IdentityToken 복호화를 위한 공개키 생성
    private PublicKey createPublicKey(ApplePublicKeyResponse.Key key) {
        byte[] nByte = Base64.getUrlDecoder().decode(key.getN());
        byte[] eByte = Base64.getUrlDecoder().decode(key.getE());

        BigInteger nValue = new BigInteger(1, nByte);
        BigInteger eValue = new BigInteger(1, eByte);

        try {
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(nValue, eValue);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(publicKeySpec);

        } catch (Exception e) {
            throw new IllegalArgumentException("No matching public key");
        }
    }

    // 클라이언트로부터 전달받은 토큰을 앞서 생성한 공개키로 복호화
    private Claims parseClaims(PublicKey publicKey, String idToken) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(idToken)
                .getBody();
    }

}
