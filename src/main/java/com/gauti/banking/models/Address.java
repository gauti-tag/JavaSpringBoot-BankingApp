package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
@Data
@Entity
@SuperBuilder
//@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractEntity {
    private String street; 

    private Integer houseNumber; 

    private Integer zipCode; 

    private String city; 

    private String country; 

    @OneToOne
    @JoinColumn(name="id_user")
    private User user; 
}
