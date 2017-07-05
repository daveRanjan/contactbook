package com.centralway.contactbook.model;

public enum Role {
    ADMIN, MEMBER;
    
    public String authority() {
        return "ROLE_" + this.name();
    }
}
