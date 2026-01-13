package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer id; 

    private String name; 
}
