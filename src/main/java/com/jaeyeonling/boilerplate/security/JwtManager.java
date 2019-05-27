package com.jaeyeonling.boilerplate.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jaeyeonling.boilerplate.exception.PlatformException;
import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import com.jaeyeonling.boilerplate.properties.ApplicationProperties;
import com.jaeyeonling.boilerplate.properties.JwtProperties;
import com.jaeyeonling.boilerplate.utils.random.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtManager {

    private final RandomUtils randomUtils;
    private final AccessTokenRepository accessTokenRepository;
    private final ApplicationProperties applicationProperties;
    private final JwtProperties jwtProperties;

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    //
    //
    //

    @Autowired
    public JwtManager(
            final RandomUtils randomUtils,
            final AccessTokenRepository accessTokenRepository,
            final ApplicationProperties applicationProperties,
            final JwtProperties jwtProperties
    ) {
        this.randomUtils = randomUtils;
        this.accessTokenRepository = accessTokenRepository;
        this.applicationProperties = applicationProperties;
        this.jwtProperties = jwtProperties;

        algorithm = Algorithm.HMAC512(jwtProperties.getSecretKey());
        verifier = JWT.require(algorithm)
                .withIssuer(getIssuer())
                .acceptExpiresAt(getRefreshMillis())
                .build();
    }

    //
    //
    //

    public String generate(final SecurityUser user) {
        final var jti = generateJWTId();

        final var encodedJWT = encode(
                jti,
                user
        );

        saveAccessToken(
                jti,
                encodedJWT
        );

        return encodedJWT;
    }

    public boolean isValid(final String accessToken) {
        return tokenVerify(accessToken) && tokenDataVerify(accessToken);
    }

    public DecodedJWT decode(final String encodedJWT) {
        return JWT.decode(encodedJWT);
    }

    //
    //
    //

    private void saveAccessToken(
            final String jti,
            final String encodedJWT
    ) {
        final var accessToken = new AccessToken();
        accessToken.setId(jti);
        accessToken.setToken(encodedJWT);
        accessToken.setTimeToLive(getExpiredMillis() + getRefreshMillis());

        accessTokenRepository.save(accessToken);
    }

    private boolean tokenVerify(final String accessToken) {
        try {
            verifier.verify(accessToken);

            return true;
        } catch(final JWTVerificationException e) {
            if(e.getMessage().contains("expired")) {
                throw PlatformException.builder().status(PlatformStatus.ACCESS_TOKEN_EXPIRED).build();
            }

            log.debug("JWT verification exception", e);

            return false;
        }
    }

    private boolean tokenDataVerify(final String accessToken) {
        final var decodedJWT = decode(accessToken);

        return accessTokenRepository.findById(decodedJWT.getId())
                .orElseThrow(() -> PlatformException.builder().status(PlatformStatus.UNAUTHORIZED).build())
                .verify(accessToken);
    }

    private String generateJWTId() {
        return randomUtils.getSecureString(jwtProperties.getJwtIdLength());
    }

    private String encode(
            final String jti,
            final SecurityUser user
    ) {
        return JWT.create()
                .withIssuer(getIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(getExpiresAt())

                // Note: Put claim set here
                .withClaim("user_id", user.getUserObjectId())
                .withClaim("role", user.getRole())
                .withClaim("username", user.getUsername())

                .withJWTId(jti)
                .withKeyId(user.getUserObjectIdString())
                .sign(algorithm);
    }

    private long getRefreshMillis() {
        return jwtProperties.getRefreshTerm().toMillis();
    }

    private long getExpiredMillis() {
        return jwtProperties.getExpiredTerm().toMillis();
    }

    private Date getExpiresAt() {
        return Date.from(Instant.now().plus(jwtProperties.getExpiredTerm()));
    }

    private String getIssuer() {
        return applicationProperties.getName();
    }
}
