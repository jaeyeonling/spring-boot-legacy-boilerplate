package com.jaeyeonling.oauth2.security.social;

import com.jaeyeonling.oauth2.type.AuthProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

class SocialLoginAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private SocialLoginAuthenticationToken(
            final Object principal,
            final Object credentials
    ) {
        super(principal, credentials);
    }

    //
    //
    //

    static SocialLoginAuthenticationToken of(final SocialLoginRequest socialLoginRequest) {
        return new SocialLoginAuthenticationToken(
                socialLoginRequest.getAuthProvider(),
                socialLoginRequest
        );
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
