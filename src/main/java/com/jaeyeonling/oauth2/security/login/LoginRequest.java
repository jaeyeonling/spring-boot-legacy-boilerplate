package com.jaeyeonling.oauth2.security.login;

import com.jaeyeonling.oauth2.type.AuthProvider;
import lombok.Getter;

@Getter
class LoginRequest {

    private String userId;
    private String password;
    private AuthProvider authProvider;
}
