package com.centralway.contactbook.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserRole {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "app_user_id")
    protected Long userId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable=false, updatable=false)
    protected Role role;

    public Role getRole() {
        return role;
    }

    public UserRole(Role role) {
        this.role = role;
    }

    public UserRole() {
    }
}
