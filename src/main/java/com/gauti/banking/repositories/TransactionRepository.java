package com.gauti.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauti.banking.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    List<Transaction> findByUserId(Integer userId);
}
