package com.jaeyeonling.oauth2.security;

import com.jaeyeonling.oauth2.security.jwt.JwtAuthenticationProvider;
import com.jaeyeonling.oauth2.security.login.LoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AuthenticationProviderManager extends ProviderManager {

    @Autowired
    public AuthenticationProviderManager(
            final LoginAuthenticationProvider loginAuthenticationProvider,
            final JwtAuthenticationProvider jwtAuthenticationProvider
    ) {
        super(
                Arrays.asList(
                        loginAuthenticationProvider,
                        jwtAuthenticationProvider
                )
        );
    }
}
