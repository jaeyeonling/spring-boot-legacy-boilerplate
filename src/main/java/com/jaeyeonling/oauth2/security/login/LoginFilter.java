package com.jaeyeonling.oauth2.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaeyeonling.oauth2.security.AuthenticationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailHandler loginFailHandler;

    //
    //
    //

    @Autowired
    private LoginFilter(
            final LoginRequestMatcher loginRequestMatcher,
            final ObjectMapper objectMapper,
            final LoginSuccessHandler loginSuccessHandler,
            final LoginFailHandler loginFailHandler,
            final AuthenticationProviderManager authenticationProviderManager
    ) {
        super(loginRequestMatcher);

        this.objectMapper = objectMapper;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailHandler = loginFailHandler;

        setAuthenticationManager(authenticationProviderManager);
    }

    //
    //
    //

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        final var loginRequest = objectMapper.readValue(
                request.getReader(),
                LoginRequest.class
        );

        return super.getAuthenticationManager().authenticate(
                LoginAuthenticationToken.of(loginRequest)
        );
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult
    ) {
        loginSuccessHandler.onAuthenticationSuccess(
                request,
                response,
                authResult
        );
    }

    @Override
    protected void unsuccessfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException failed
    ) {
        loginFailHandler.onAuthenticationFailure(
                request,
                response,
                failed
        );
    }
}
