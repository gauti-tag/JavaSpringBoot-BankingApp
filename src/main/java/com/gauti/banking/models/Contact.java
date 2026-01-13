package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity

@Builder
public class Contact {
    @Id
    @GeneratedValue
    private Integer id; 
}
