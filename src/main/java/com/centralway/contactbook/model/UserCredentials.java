package com.centralway.contactbook.model;

import javax.persistence.*;

@Entity
public class UserCredentials {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "id")
    private User user;
    private String userName;
    private String passwordHash;
    private String role;
}
