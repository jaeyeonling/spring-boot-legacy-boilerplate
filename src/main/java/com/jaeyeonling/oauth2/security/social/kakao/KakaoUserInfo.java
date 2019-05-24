package com.jaeyeonling.oauth2.security.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaeyeonling.oauth2.security.social.SocialUserInfo;
import com.jaeyeonling.oauth2.type.AuthProvider;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
public class KakaoUserInfo implements SocialUserInfo {

    private long id;

    private KakaoProperties properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    //
    //
    //

    @Override
    public AuthProvider getAuthProvider() {
        return AuthProvider.KAKAO;
    }

    @Override
    public String getUserId() {
        return Long.toString(id);
    }

    @Override
    public String getNickname() {
        return properties.getNickname();
    }

    @Override
    public @Nullable String getEmail() {
        return kakaoAccount.getEmail();
    }
}
