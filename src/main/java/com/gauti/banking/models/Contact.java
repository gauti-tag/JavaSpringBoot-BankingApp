package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact extends AbstractEntity {
    private String firstname; 

    private String lastname; 
    
    private String email; 

    private String iban; 

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user; 
}
