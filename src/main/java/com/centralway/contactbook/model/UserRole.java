package com.centralway.contactbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class UserRole {

    @Enumerated(EnumType.STRING) public Role role;

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
