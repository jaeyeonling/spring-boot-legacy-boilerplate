package com.jaeyeonling.boilerplate.security.login;

import com.jaeyeonling.boilerplate.type.AuthProvider;
import lombok.Getter;

@Getter
class LoginRequest {

    private String userId;
    private String password;
    private AuthProvider authProvider;
}
