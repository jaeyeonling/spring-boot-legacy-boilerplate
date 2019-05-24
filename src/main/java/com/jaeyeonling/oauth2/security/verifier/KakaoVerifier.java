package com.jaeyeonling.oauth2.security.verifier;

import com.jaeyeonling.oauth2.entity.Authentication;
import com.jaeyeonling.oauth2.security.social.SocialLoginRequest;
import com.jaeyeonling.oauth2.security.social.kakao.KakaoFetchService;
import com.jaeyeonling.oauth2.type.AuthProvider;
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
