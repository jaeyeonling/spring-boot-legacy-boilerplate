package com.jaeyeonling.oauth2.security.social;

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
public class SocialLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;
    private final SocialLoginSuccessHandler socialLoginSuccessHandler;
    private final SocialLoginFailHandler socialLoginFailHandler;

    //
    //
    //

    @Autowired
    public SocialLoginFilter(
            final SocialLoginRequestMatcher socialLoginRequestMatcher,
            final ObjectMapper objectMapper,
            final SocialLoginSuccessHandler socialLoginSuccessHandler,
            final SocialLoginFailHandler socialLoginFailHandler,
            final AuthenticationProviderManager authenticationProvider
    ) {
        super(socialLoginRequestMatcher);

        this.objectMapper = objectMapper;
        this.socialLoginSuccessHandler = socialLoginSuccessHandler;
        this.socialLoginFailHandler = socialLoginFailHandler;

        setAuthenticationManager(authenticationProvider);
    }

    //
    //
    //

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        final var socialLoginRequest = objectMapper.readValue(
                request.getReader(),
                SocialLoginRequest.class
        );

        return super.getAuthenticationManager().authenticate(
                SocialLoginAuthenticationToken.of(socialLoginRequest)
        );
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult
    ) {
        socialLoginSuccessHandler.onAuthenticationSuccess(
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
        socialLoginFailHandler.onAuthenticationFailure(
                request,
                response,
                failed
        );
    }
}
