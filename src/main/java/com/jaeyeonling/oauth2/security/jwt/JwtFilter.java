package com.jaeyeonling.oauth2.security.jwt;

import com.jaeyeonling.oauth2.properties.JwtProperties;
import com.jaeyeonling.oauth2.security.AuthenticationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtHeaderExtractor jwtHeaderExtractor;
    private final JwtSuccessHandler jwtSuccessHandler;
    private final JwtFailHandler jwtFailHandler;

    private final String jwtHeaderName;

    //
    //
    //

    @Autowired
    private JwtFilter(
            final JwtRequestMatcher jwtRequestMatcher,
            final JwtHeaderExtractor jwtHeaderExtractor,
            final JwtSuccessHandler jwtSuccessHandler,
            final JwtFailHandler jwtFailHandler,
            final JwtProperties jwtProperties,
            final AuthenticationProviderManager authenticationProviderManager
    ) {
        super(jwtRequestMatcher);

        this.jwtHeaderExtractor = jwtHeaderExtractor;
        this.jwtSuccessHandler = jwtSuccessHandler;
        this.jwtFailHandler = jwtFailHandler;

        jwtHeaderName = jwtProperties.getHeaderName();

        setAuthenticationManager(authenticationProviderManager);
    }

    //
    //
    //

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {
        final var tokenValue = request.getHeader(jwtHeaderName);
        final var accessTokenValue = jwtHeaderExtractor.extract(tokenValue);

        return super.getAuthenticationManager().authenticate(
                new JwtAuthenticationToken(accessTokenValue)
        );
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult
    ) throws ServletException, IOException {
        jwtSuccessHandler.onAuthenticationSuccess(
                request,
                response,
                authResult
        );

        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException failed
    ) {
        jwtFailHandler.onAuthenticationFailure(
                request,
                response,
                failed
        );
    }
}
