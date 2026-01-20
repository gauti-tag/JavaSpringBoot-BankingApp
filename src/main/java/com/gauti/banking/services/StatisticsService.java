package com.gauti.banking.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.gauti.banking.dto.TransactionSumDetails;

public interface StatisticsService {

    List<TransactionSumDetails> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId);
    BigDecimal getAccountBalance(Integer userId);
    BigDecimal highestTransfer(Integer userId);
    BigDecimal highestDeposit(Integer userId);

}
