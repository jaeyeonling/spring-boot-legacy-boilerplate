package com.jaeyeonling.boilerplate.security.social.github;

import com.jaeyeonling.boilerplate.properties.SecurityProperties;
import com.jaeyeonling.boilerplate.security.social.SocialFetchService;
import com.jaeyeonling.boilerplate.security.social.SocialUserInfo;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import com.jaeyeonling.boilerplate.utils.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubFetchService extends SocialFetchService {

    @Autowired
    public GithubFetchService(
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
    protected AuthProvider getAuthProvider() {
        return AuthProvider.GITHUB;
    }

    @Override
    protected Class<? extends SocialUserInfo> getSocialUserInfoType() {
        return GithubUserInfo.class;
    }
}
