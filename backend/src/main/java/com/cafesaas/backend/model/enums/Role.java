package com.cafesaas.backend.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    // İşletme sahibi (User Entity için)
    OWNER("İşletme Sahibi"),

    // Kasa/Terminal (Cafe Entity için - Machine Account)
    CAFE_TERMINAL("Kasa Terminali");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}