package com.jaeyeonling.boilerplate.model;

import com.jaeyeonling.boilerplate.type.UserRole;
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
