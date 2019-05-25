package com.jaeyeonling.boilerplate.security.verifier;

import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.security.social.SocialLoginRequest;
import com.jaeyeonling.boilerplate.security.social.kakao.KakaoFetchService;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KakaoVerifier implements Verifier {

    private final KakaoFetchService kakaoFetchService;

    //
    //
    //

    @Autowired
    public KakaoVerifier(final KakaoFetchService kakaoFetchService) {
        this.kakaoFetchService = kakaoFetchService;
    }

    //
    //
    //

    @Override
    public boolean verify(
            final Authentication authentication,
            final String token
    ) {
        final var socialLoginRequest = new SocialLoginRequest(
                AuthProvider.KAKAO,
                token
        );

        final var userId = kakaoFetchService.getSocialUserInfo(socialLoginRequest).getUserId();

        return authentication.getUserId().equals(userId);
    }
}
