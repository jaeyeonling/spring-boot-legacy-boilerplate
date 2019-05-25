package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.security.social.kakao.KakaoFetchService;
import com.jaeyeonling.boilerplate.type.AuthProvider;
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
