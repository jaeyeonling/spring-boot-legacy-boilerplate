package com.jaeyeonling.boilerplate.security.verifier;

import com.jaeyeonling.boilerplate.security.social.naver.NaverFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NaverVerifier extends SocialVerifier {

    @Autowired
    public NaverVerifier(final NaverFetchService naverFetchService) {
        super(naverFetchService);
    }
}
