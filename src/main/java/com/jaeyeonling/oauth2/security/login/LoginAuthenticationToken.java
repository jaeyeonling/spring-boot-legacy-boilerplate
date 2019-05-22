package com.jaeyeonling.oauth2.security.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private LoginAuthenticationToken(
            final Object principal,
            final Object credentials
    ) {
        super(principal, credentials);
    }

    //
    //
    //

    public static LoginAuthenticationToken of(final LoginRequest loginRequest) {
        return new LoginAuthenticationToken(
                loginRequest.getUserId(),
                loginRequest.getPassword()
        );
    }

    //
    //
    //

    public String getUserId() {
        return (String) getPrincipal();
    }

    public String getPassword() {
        return (String) getCredentials();
    }
}
