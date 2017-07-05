package com.centralway.contactbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne @JsonIgnore User user;

    private String firstName;
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "phone")
    private Set<String> phones;
}
