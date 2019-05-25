package com.jaeyeonling.boilerplate.type;

public enum UserRole {

    ADMIN,
    USER,

    ;

    @Override
    public String toString() {
        return "ROLE_" + this.name();
    }
}
