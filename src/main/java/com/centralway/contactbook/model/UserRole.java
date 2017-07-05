package com.centralway.contactbook.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class UserRole {

    @Column(name = "app_user_id")
    protected Long userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable=false, updatable=false)
    protected Role role;
    @Id
    @GeneratedValue
    private Long id;

    public UserRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

}
