package com.jaeyeonling.boilerplate.security.verifier;

import com.jaeyeonling.boilerplate.type.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifierProvider {

    private final ServerVerifier serverVerifier;
    private final KakaoVerifier kakaoVerifier;
    private final GithubVerifier githubVerifier;

    //
    //
    //

    @Autowired
    public VerifierProvider(
            final ServerVerifier serverVerifier,
            final KakaoVerifier kakaoVerifier,
            final GithubVerifier githubVerifier
    ) {
        this.serverVerifier = serverVerifier;
        this.kakaoVerifier = kakaoVerifier;
        this.githubVerifier = githubVerifier;
    }

    //
    //
    //

    public Verifier getVerifier(final AuthProvider authProvider) {
        switch (authProvider) {
            case SERVER:
                return serverVerifier;
            case KAKAO:
                return kakaoVerifier;
            case GITHUB:
                return githubVerifier;
        }

        throw new IllegalArgumentException("Not support auth provider");
    }
}
