package com.jaeyeonling.oauth2.security.social;

import com.jaeyeonling.oauth2.security.social.kakao.KakaoFetchService;
import com.jaeyeonling.oauth2.type.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocialFetchServiceProvider {

    private final KakaoFetchService kakaoFetchService;

    //
    //
    //

    @Autowired
    public SocialFetchServiceProvider(final KakaoFetchService kakaoFetchService) {
        this.kakaoFetchService = kakaoFetchService;
    }

    //
    //
    //

    SocialFetchService getSocialFetchService(final AuthProvider authProvider) {
        switch (authProvider) {
            case KAKAO:
                return kakaoFetchService;
        }

        throw new IllegalArgumentException("Not support auth provider");
    }
}
