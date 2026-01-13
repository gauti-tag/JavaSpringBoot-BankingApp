package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
@Data
@Entity
@Builder
public class Address {
    @Id
    @GeneratedValue
    private Integer id; 

    private String street; 

    private Integer houseNumber; 

    private Integer zipCode; 

    private String city; 

    private String country; 
}
