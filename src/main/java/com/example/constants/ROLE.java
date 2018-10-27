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

    public static ROLE getRoleByKey(String key){
        for (ROLE value : ROLE.values()) {
            if(value.getRoleString().equals(key)){
                return value;
            }
        }
        return UNKNOWN;
    }
}
