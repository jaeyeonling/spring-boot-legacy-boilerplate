package com.jaeyeonling.oauth2.security.jwt;

import com.jaeyeonling.oauth2.exception.PlatformException;
import com.jaeyeonling.oauth2.exception.PlatformStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException exception
    ) {
        throw PlatformException.builder().status(PlatformStatus.UNAUTHORIZED).build();
    }
}
