package com.jaeyeonling.boilerplate.security.login;

import com.jaeyeonling.boilerplate.properties.JwtProperties;
import com.jaeyeonling.boilerplate.security.JwtManager;
import com.jaeyeonling.boilerplate.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProperties jwtProperties;
    private final JwtManager jwtManager;

    //
    //
    //

    @Autowired
    public LoginSuccessHandler(
            final JwtProperties jwtProperties,
            final JwtManager jwtManager
    ) {
        this.jwtProperties = jwtProperties;
        this.jwtManager = jwtManager;
    }

    //
    //
    //

    @Override
    public void onAuthenticationSuccess(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication
    ) {
        final var authenticationToken = (LoginAuthenticationToken) authentication;
        final var securityUser = (SecurityUser) authenticationToken.getDetails();

        final var accessToken = jwtManager.generate(securityUser);

        response.setHeader(jwtProperties.getHeaderName(), "Bearer " + accessToken);
    }
}
