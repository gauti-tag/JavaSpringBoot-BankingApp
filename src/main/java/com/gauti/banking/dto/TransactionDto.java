package com.gauti.banking.dto;

import java.math.BigDecimal;

import com.gauti.banking.models.Transaction;
import com.gauti.banking.models.TransactionType;
import com.gauti.banking.models.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id; 

    @Positive
    @Max(value=100000)
    @Min(value=20)
    private BigDecimal amount; 

    private TransactionType type; 

    private String destinationIban; 

    private Integer userId; 

    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
            .id(transaction.getId())
            .amount(transaction.getAmount())
            .destinationIban(transaction.getDestinationIban())
            .type(transaction.getType())
            .userId(transaction.getUser().getId())
            .build();
    }


    public static Transaction toEntity(TransactionDto transaction){
        return Transaction.builder()
            .id(transaction.getId())
            .amount(transaction.getAmount())
            .destinationIban(transaction.getDestinationIban())
            .type(transaction.getType())
            .user(
                User.builder()
                    .id(transaction.getUserId())
                    .build()
            )
            .build();
    }

}
