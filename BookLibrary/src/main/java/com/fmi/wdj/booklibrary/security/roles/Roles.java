package com.fmi.wdj.booklibrary.security.roles;

import lombok.Getter;

@Getter
public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    private Roles(String role) {
        this.role = role;
    }
}
