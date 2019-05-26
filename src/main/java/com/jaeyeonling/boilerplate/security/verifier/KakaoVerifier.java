package com.jaeyeonling.boilerplate.security.verifier;

import com.jaeyeonling.boilerplate.security.social.kakao.KakaoFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KakaoVerifier extends SocialVerifier {

    @Autowired
    public KakaoVerifier(final KakaoFetchService kakaoFetchService) {
        super(kakaoFetchService);
    }
}