package com.leftovers.restaurants.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leftovers.restaurants.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTUtil {
    private final SecurityProperties securityProperties;

    public JWTUser validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        DecodedJWT jwt = JWT.decode(token);

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(securityProperties.getSecret()))
                .withSubject("User Details")
                .withIssuer("Leftovers User Issuer")
                .build();

        verifier.verify(jwt);
        return JWTUser.builder()
                .id(jwt.getClaim("userId").asLong())
                .email(jwt.getClaim("email").asString())
                .role(jwt.getClaim("role").asString())
                .build();
    }
}
