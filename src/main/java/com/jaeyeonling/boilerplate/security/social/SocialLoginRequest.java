package com.jaeyeonling.boilerplate.security.social;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor // Note: Using ObjectMapper
@RequiredArgsConstructor(staticName = "token")
public class SocialLoginRequest {

    @NonNull
    private String token;
}
