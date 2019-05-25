package com.jaeyeonling.boilerplate.security.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaeyeonling.boilerplate.exception.PlatformException;
import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import com.jaeyeonling.boilerplate.security.AuthenticationProviderManager;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

        final var socialLoginAuthentication = new SocialLoginAuthenticationToken(
                getAuthProvider(request),
                socialLoginRequest
        );

        return super.getAuthenticationManager().authenticate(
                socialLoginAuthentication
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

    //
    //
    //

    private AuthProvider getAuthProvider(final HttpServletRequest httpServletRequest) {
        final var lastURI = getLastURI(httpServletRequest);

        return AuthProvider.of(lastURI)
                .orElseThrow(() -> PlatformException.builder().status(PlatformStatus.BAD_REQUEST).build());
    }

    private String getLastURI(final HttpServletRequest httpServletRequest) {
        final var requestURI = httpServletRequest.getRequestURI();

        final var lastSlashPosition = requestURI.lastIndexOf("/");
        if (lastSlashPosition < 0) {
            return requestURI;
        }

        return requestURI.substring(lastSlashPosition + 1);
    }
}
