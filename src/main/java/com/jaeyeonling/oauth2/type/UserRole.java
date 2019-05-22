package com.jaeyeonling.oauth2.type;

public enum UserRole {
    ADMIN,
    USER,

    ;

    @Override
    public String toString() {
        return "ROLE_" + this.name();
    }
}
