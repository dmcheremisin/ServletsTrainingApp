package com.example.constants;

public enum ROLE {
    UNKNOWN("unknown"), MEMBER("member"), ADMIN("admin");

    private String role;

    ROLE(String role) {
        this.role = role;
    }

    public String getRoleString() {
        return role;
    }
}
