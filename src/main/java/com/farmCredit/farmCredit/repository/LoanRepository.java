package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
