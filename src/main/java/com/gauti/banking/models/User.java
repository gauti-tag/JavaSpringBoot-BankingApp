package com.gauti.banking.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
public class User extends AbstractEntity {

    private String firstname; 

    private String lastname;
    
    @Column(unique = true)
    private String email; 

    private String password; 

    private boolean active; 

    @OneToOne(mappedBy="user")
    private Address address; 

    @OneToMany(mappedBy="user")
    private List<Transaction> transactions; 

    @OneToMany(mappedBy="user")
    private List<Contact> contact;

    @OneToOne(mappedBy="user")
    private Account account; 

    @OneToOne(mappedBy="user")
    private Role role;
}
