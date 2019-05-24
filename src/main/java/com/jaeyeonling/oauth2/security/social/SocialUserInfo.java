package com.jaeyeonling.oauth2.security.social;

import com.jaeyeonling.oauth2.type.AuthProvider;

public interface SocialUserInfo {

    AuthProvider getAuthProvider();

    String getUserId();
    String getNickname();
    String getEmail();
}
