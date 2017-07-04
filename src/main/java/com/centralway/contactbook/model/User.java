package com.centralway.contactbook.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String passwordHash;
}
