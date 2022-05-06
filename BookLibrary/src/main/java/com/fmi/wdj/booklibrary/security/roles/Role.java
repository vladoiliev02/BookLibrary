package com.fmi.wdj.booklibrary.security.roles;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

import static com.fmi.wdj.booklibrary.security.roles.Authority.*;

@Getter
public enum Role {
    USER("USER", EnumSet.of(BOOK_WRITE, BOOK_READ, SELF_USER_WRITE, SELF_USER_READ)),
    ADMIN("ADMIN", EnumSet.allOf(Authority.class));

    private final String role;
    private final Set<Authority> authorities;

    private Role(String role, Set<Authority> authorities) {
        this.role = role;
        this.authorities = authorities;
    }
}
