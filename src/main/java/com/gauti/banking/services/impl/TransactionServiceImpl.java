package com.gauti.banking.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gauti.banking.dto.TransactionDto;
import com.gauti.banking.models.Transaction;
import com.gauti.banking.models.TransactionType;
import com.gauti.banking.repositories.TransactionRepository;
import com.gauti.banking.services.TransactionService;
import com.gauti.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository repository;
    private final ObjectsValidator<TransactionDto> validator;
    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        transaction.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(transactionType(transaction.getType()))));
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
            .stream()
            .map(TransactionDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
            .map(TransactionDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException("No Transaction was found with ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check and delete 
        repository.deleteById(id);
    }

    private int transactionType(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;  
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findByUserId(userId)
            .stream()
            .map(TransactionDto::fromEntity)
            .collect(Collectors.toList());
    }

}
