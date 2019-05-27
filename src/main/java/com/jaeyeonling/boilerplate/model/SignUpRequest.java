package com.jaeyeonling.boilerplate.model;

import com.jaeyeonling.boilerplate.entity.Account;
import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.security.PasswordEncoder;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import com.jaeyeonling.boilerplate.type.UserRole;
import lombok.Setter;

@Setter
public class SignUpRequest {

    private String userId;
    private UserRole userRole;

    private String password;
    private String username;
    private String email;

    //
    //
    //

    public Authentication toEntity(final PasswordEncoder passwordEncoder) {
        final var account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        account.setRole(userRole);

        final var authentication = new Authentication();
        authentication.setAccount(account);
        authentication.setAuthProvider(AuthProvider.SERVER);
        authentication.setUserId(userId);
        authentication.setPassword(passwordEncoder.encode(password));

        return authentication;
    }
}
