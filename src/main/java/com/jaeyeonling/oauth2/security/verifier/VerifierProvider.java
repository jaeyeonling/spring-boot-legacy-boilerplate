package com.jaeyeonling.oauth2.security.verifier;

import com.jaeyeonling.oauth2.type.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifierProvider {

    private final ServerVerifier serverVerifier;
    private final KakaoVerifier kakaoVerifier;

    //
    //
    //

    @Autowired
    public VerifierProvider(
            final ServerVerifier serverVerifier,
            final KakaoVerifier kakaoVerifier
    ) {
        this.serverVerifier = serverVerifier;
        this.kakaoVerifier = kakaoVerifier;
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
        }

        throw new IllegalArgumentException("Not support auth provider");
    }
}
