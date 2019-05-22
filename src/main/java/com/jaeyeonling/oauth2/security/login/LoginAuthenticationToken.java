package com.jaeyeonling.oauth2.security.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

class LoginAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private LoginAuthenticationToken(
            final Object principal,
            final Object credentials
    ) {
        super(principal, credentials);
    }

    //
    //
    //

    static LoginAuthenticationToken of(final LoginRequest loginRequest) {
        return new LoginAuthenticationToken(
                loginRequest.getUserId(),
                loginRequest.getPassword()
        );
    }

    //
    //
    //

    String getUserId() {
        return (String) getPrincipal();
    }

    String getPassword() {
        return (String) getCredentials();
    }
}
