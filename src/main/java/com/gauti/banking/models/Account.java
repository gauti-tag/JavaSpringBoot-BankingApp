package com.gauti.banking.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id; 

    private String iban; 

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdated; 

   // private Address address; 
}
