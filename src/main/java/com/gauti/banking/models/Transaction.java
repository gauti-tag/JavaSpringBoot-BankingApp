package com.gauti.banking.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id; 

    private BigDecimal amount; 

    @Enumerated(EnumType.STRING)
    private TransactionType type; 

    private String destinationIban; 

    private LocalDateTime creationDate; 

    private LocalDateTime lastUpdated;

}
