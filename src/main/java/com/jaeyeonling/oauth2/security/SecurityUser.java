package com.jaeyeonling.oauth2.security;

import com.jaeyeonling.oauth2.entity.Authentication;
import com.jaeyeonling.oauth2.type.UserRole;
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

    public long getOwner() {
        return authentication.getOwner();
    }

    public String getOwnerString() {
        return Long.toString(getOwner());
    }

    public String getRole() {
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
