package com.gauti.banking.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauti.banking.models.Transaction;
import com.gauti.banking.models.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    List<Transaction> findByUserId(Integer userId);

    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.creationDate, sum(t.amount) from Transaction t where t.user.id = :userId and t.creationDate between :start and :end group by t.createDate")
    Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDateTime start, LocalDateTime end, Integer userId);
    
}
