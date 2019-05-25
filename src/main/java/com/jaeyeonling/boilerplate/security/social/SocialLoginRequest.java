package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.type.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // Note: Using ObjectMapper
@AllArgsConstructor
public class SocialLoginRequest {

    private AuthProvider authProvider;
    private String token;
}
