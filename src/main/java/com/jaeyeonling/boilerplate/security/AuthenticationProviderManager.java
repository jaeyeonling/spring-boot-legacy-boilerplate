package com.jaeyeonling.boilerplate.security;

import com.jaeyeonling.boilerplate.security.jwt.JwtAuthenticationProvider;
import com.jaeyeonling.boilerplate.security.login.LoginAuthenticationProvider;
import com.jaeyeonling.boilerplate.security.social.SocialLoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AuthenticationProviderManager extends ProviderManager {

    @Autowired
    public AuthenticationProviderManager(
            final LoginAuthenticationProvider loginAuthenticationProvider,
            final JwtAuthenticationProvider jwtAuthenticationProvider,
            final SocialLoginAuthenticationProvider socialLoginAuthenticationProvider
    ) {
        super(
                Arrays.asList(
                        loginAuthenticationProvider,
                        jwtAuthenticationProvider,
                        socialLoginAuthenticationProvider
                )
        );
    }
}
