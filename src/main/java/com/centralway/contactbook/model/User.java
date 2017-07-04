package com.centralway.contactbook.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "phone")
    private Set<String> phones;
}
