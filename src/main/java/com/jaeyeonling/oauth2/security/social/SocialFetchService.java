package com.jaeyeonling.oauth2.security.social;

import org.springframework.stereotype.Service;

@Service
public interface SocialFetchService {

    SocialUserInfo getSocialUserInfo(final SocialLoginRequest socialLoginRequest);
}
