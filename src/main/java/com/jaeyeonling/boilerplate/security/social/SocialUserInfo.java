package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.entity.Account;
import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import com.jaeyeonling.boilerplate.type.UserRole;

public abstract class SocialUserInfo {

    Authentication toAuthentication() {
        final var account = new Account();
        account.setUsername(getNickname());
        account.setEmail(getEmail());
        account.setRole(UserRole.USER);

        final var authentication = new Authentication();
        authentication.setAccount(account);
        authentication.setAuthProvider(getAuthProvider());
        authentication.setUserId(getUserId());
        authentication.setPassword(getUserId()); // Note: password not null

        return authentication;
    }

    //
    //
    //

    public abstract String getUserId();

    protected abstract AuthProvider getAuthProvider();
    protected abstract String getNickname();
    protected abstract String getEmail();
}
