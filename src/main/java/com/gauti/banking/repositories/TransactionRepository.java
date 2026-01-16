package com.gauti.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauti.banking.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
