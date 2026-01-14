package com.gauti.banking.models;

import java.math.BigDecimal;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
//@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends AbstractEntity {

    private BigDecimal amount; 

    //@Enumerated(EnumType.STRING)
    //private TransactionType type; 

    private String destinationIban; 

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;
}
