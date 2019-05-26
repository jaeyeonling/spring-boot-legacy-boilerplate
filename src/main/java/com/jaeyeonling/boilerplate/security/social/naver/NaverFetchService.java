package com.jaeyeonling.boilerplate.security.social.naver;

import com.jaeyeonling.boilerplate.properties.SecurityProperties;
import com.jaeyeonling.boilerplate.security.social.SocialFetchService;
import com.jaeyeonling.boilerplate.security.social.SocialLoginRequest;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import com.jaeyeonling.boilerplate.utils.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverFetchService extends SocialFetchService<NaverUserInfo> {

    @Autowired
    public NaverFetchService(
            final SecurityProperties securityProperties,
            final RestTemplate restTemplate
    ) {
        super(
                securityProperties,
                restTemplate
        );
    }

    //
    //
    //

    @Override
    public NaverUserInfo getSocialUserInfo(SocialLoginRequest socialLoginRequest) {
        final var naverUserInfo = super.getSocialUserInfo(socialLoginRequest);

        naverUserInfo.responseCheck();

        return naverUserInfo;
    }


    //
    //
    //

    @Override
    protected AuthProvider getAuthProvider() {
        return AuthProvider.NAVER;
    }

    @Override
    protected Class<NaverUserInfo> getSocialUserInfoType() {
        return NaverUserInfo.class;
    }
}
