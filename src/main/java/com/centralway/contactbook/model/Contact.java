package com.centralway.contactbook.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne User user;

    private String firstName;
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "phone")
    private Set<String> phones;
}
