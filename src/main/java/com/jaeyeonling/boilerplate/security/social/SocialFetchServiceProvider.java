package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.security.social.github.GithubFetchService;
import com.jaeyeonling.boilerplate.security.social.kakao.KakaoFetchService;
import com.jaeyeonling.boilerplate.security.social.naver.NaverFetchService;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocialFetchServiceProvider {

    private final KakaoFetchService kakaoFetchService;
    private final GithubFetchService githubFetchService;
    private final NaverFetchService naverFetchService;

    //
    //
    //

    @Autowired
    public SocialFetchServiceProvider(
            final KakaoFetchService kakaoFetchService,
            final GithubFetchService githubFetchService,
            final NaverFetchService naverFetchService
    ) {
        this.kakaoFetchService = kakaoFetchService;
        this.githubFetchService = githubFetchService;
        this.naverFetchService = naverFetchService;
    }

    //
    //
    //

    SocialFetchService getSocialFetchService(final AuthProvider authProvider) {
        switch (authProvider) {
            case KAKAO:
                return kakaoFetchService;
            case GITHUB:
                return githubFetchService;
            case NAVER:
                return naverFetchService;
        }

        throw new IllegalArgumentException("Not support auth provider");
    }
}
