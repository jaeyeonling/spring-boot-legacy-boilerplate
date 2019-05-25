package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.type.AuthProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

class SocialLoginAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public SocialLoginAuthenticationToken(
            final Object authProvider,
            final Object socialLoginRequest
    ) {
        super(authProvider, socialLoginRequest);
    }

    //
    //
    //

    AuthProvider getAuthProvider() {
        return (AuthProvider) getPrincipal();
    }

    SocialLoginRequest getSocialLoginRequest() {
        return (SocialLoginRequest) getCredentials();
    }
}
