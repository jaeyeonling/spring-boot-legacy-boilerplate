package com.jaeyeonling.oauth2.security.social;

import com.jaeyeonling.oauth2.type.AuthProvider;
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
