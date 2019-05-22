package com.jaeyeonling.oauth2.security.jwt;

import com.jaeyeonling.oauth2.exception.PlatformException;
import com.jaeyeonling.oauth2.exception.PlatformStatus;
import com.jaeyeonling.oauth2.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtManager jwtManager;

    //
    //
    //

    @Autowired
    private JwtAuthenticationProvider(final JwtManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    //
    //
    //

    @Override
    public Authentication authenticate(final Authentication input) throws AuthenticationException {
        final var jwtAuthenticationToken = (JwtAuthenticationToken) input;
        final var accessToken = jwtAuthenticationToken.getToken();

        if (!jwtManager.isValid(accessToken)) {
            throw PlatformException.builder().status(PlatformStatus.INVALID_ACCESS_TOKEN).build();
        }

        jwtAuthenticationToken.bind(jwtManager.decode(accessToken));

        if (jwtAuthenticationToken.isExpired()) {
            // TODO: refresh
        }

        return jwtAuthenticationToken;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return JwtAuthenticationToken.class.isAssignableFrom(clazz);
    }
}
