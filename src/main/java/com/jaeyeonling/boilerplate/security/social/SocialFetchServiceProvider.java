package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.security.social.github.GithubFetchService;
import com.jaeyeonling.boilerplate.security.social.kakao.KakaoFetchService;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocialFetchServiceProvider {

    private final KakaoFetchService kakaoFetchService;
    private final GithubFetchService githubFetchService;

    //
    //
    //

    @Autowired
    public SocialFetchServiceProvider(
            final KakaoFetchService kakaoFetchService,
            final GithubFetchService githubFetchService
    ) {
        this.kakaoFetchService = kakaoFetchService;
        this.githubFetchService = githubFetchService;
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
        }

        throw new IllegalArgumentException("Not support auth provider");
    }
}
