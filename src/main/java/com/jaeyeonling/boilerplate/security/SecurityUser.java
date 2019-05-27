package com.jaeyeonling.boilerplate.security;

import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.type.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecurityUser extends User {

    private final Authentication authentication;

    //
    //
    //

    public SecurityUser(final Authentication authentication) {
        super(
            authentication.getUserId(),
            authentication.getPassword(),
            roleToAuthorities(authentication.getRole())
        );

        this.authentication = authentication;
    }

    //
    //
    //

    long getUserObjectId() {
        return authentication.getUserObjectId();
    }

    String getUserObjectIdString() {
        return Long.toString(getUserObjectId());
    }

    String getRole() {
        return authentication.getRole().toString();
    }

    //
    //
    //

    private static List<SimpleGrantedAuthority> roleToAuthorities(final UserRole role) {
        return Stream.of(role)
                .map(UserRole::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
