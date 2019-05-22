package com.jaeyeonling.oauth2.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JwtAuthenticationToken implements Authentication {

    @Getter
    private boolean authenticated = false;

    @Getter
    private String name; // Username

    @Getter
    private Object credentials; // Access Token

    @Getter
    private Object details; // Decode jwt token

    @Getter
    private Object principal; // This object

    @Getter
    private Collection<? extends GrantedAuthority> authorities; // Role

    @Getter
    private long userId;

    //
    //
    //

    JwtAuthenticationToken(final String token) {
        credentials = token;
    }

    //
    //
    //

    @Override
    public void setAuthenticated(final boolean ignore) { }

    public String getJwtId() {
        return getDecodedJWT().getId();
    }

    public String getKeyId() {
        return getDecodedJWT().getKeyId();
    }

    //
    //
    //

    boolean isExpired() {
        return getDecodedJWT().getExpiresAt().getTime() <= System.currentTimeMillis();
    }

    String getToken() {
        return (String) credentials;
    }

    void bind(final DecodedJWT decodedJWT) {
        details = decodedJWT;

        bind();
    }

    //
    //
    //

    private DecodedJWT getDecodedJWT() {
        return (DecodedJWT) details;
    }

    private void bind() {
        authorities =  singleGrantedAuthority(getRoleFromClaim());
        userId = getUserIdFromClaim();
        name = getUsernameFromClaim();
        principal = this;
        authenticated = true;
    }

    private List<GrantedAuthority> singleGrantedAuthority(final String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    private String getRoleFromClaim() {
        return getDecodedJWT().getClaim("role").asString();
    }

    private String getUsernameFromClaim() {
        return getDecodedJWT().getClaim("username").asString();
    }

    private long getUserIdFromClaim() {
        return getDecodedJWT().getClaim("user_id").asLong();
    }
}
