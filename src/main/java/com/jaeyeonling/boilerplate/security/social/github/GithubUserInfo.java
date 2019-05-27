package com.jaeyeonling.boilerplate.security.social.github;

import com.jaeyeonling.boilerplate.security.social.SocialUserInfo;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
public class GithubUserInfo extends SocialUserInfo {

    private long id;

    private String name;

    private String email;

    //
    //
    //

    @Override
    public AuthProvider getAuthProvider() {
        return AuthProvider.GITHUB;
    }

    @Override
    public String getUserId() {
        return Long.toString(id);
    }

    @Override
    public String getNickname() {
        return name;
    }

    @Override
    public @Nullable String getEmail() {
        return email;
    }
}
