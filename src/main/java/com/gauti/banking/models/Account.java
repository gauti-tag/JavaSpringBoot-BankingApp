package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Account extends AbstractEntity {

    private String iban; 

   // private Address address; 

   @OneToOne
   @JoinColumn(name="id_user")
   private User user; 
}
