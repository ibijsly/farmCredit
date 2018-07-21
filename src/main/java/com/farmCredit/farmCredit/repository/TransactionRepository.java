package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
