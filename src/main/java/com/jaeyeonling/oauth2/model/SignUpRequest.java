package com.jaeyeonling.oauth2.model;

import com.jaeyeonling.oauth2.type.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    private String userId;
    private UserRole userRole;

    private String password;
    private String username;
    private String email;
}
