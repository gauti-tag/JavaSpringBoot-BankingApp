package com.gauti.banking.services;

import java.util.List;

import com.gauti.banking.dto.TransactionDto;

public interface TransactionService extends AbstractService<TransactionDto> {
    List<TransactionDto> findAllByUserId(Integer userId);
}
