package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id; 

    private String firstname; 

    private String lastname;
    
    private String email; 

    private String password; 

    private boolean active; 

   // private Address address; 
}
