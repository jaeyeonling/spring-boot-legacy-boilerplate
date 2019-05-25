package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.type.AuthProvider;

public interface SocialUserInfo {

    AuthProvider getAuthProvider();

    String getUserId();
    String getNickname();
    String getEmail();
}
