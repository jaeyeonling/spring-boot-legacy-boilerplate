package com.jaeyeonling.boilerplate.security.social.naver;

import com.jaeyeonling.boilerplate.security.social.SocialUserInfo;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
public class NaverUserInfo extends SocialUserInfo {

    private NaverResponseCode resultCode;

    private NaverResponse response;

    //
    //
    //

    void responseCheck() {
        resultCode.check();
    }

    @Override
    public AuthProvider getAuthProvider() {
        return AuthProvider.NAVER;
    }

    @Override
    public String getUserId() {
        return response.getId();
    }

    @Override
    public String getNickname() {
        return response.getNickname();
    }

    @Override
    public @Nullable String getEmail() {
        return response.getEmail();
    }
}
