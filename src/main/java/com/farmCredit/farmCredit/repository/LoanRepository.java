package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM loan WHERE farmer_id = ?1")
    Long getTotalCount(Long farmerId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM loan WHERE farmer_id = ?1 AND payment_mode = 'CASH'")
    Long getCashCount(Long farmerId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM loan WHERE farmer_id = ?1 AND payment_mode = 'PRODUCE'")
    Long getProduceCount(Long farmerId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM loan WHERE farmer_id = ?1 AND status = 3")
    Long getDebtCount(long id);


    @Query(nativeQuery = true, value = "SELECT SUM(value) FROM loan WHERE farmer_id = ?1")
    Double getTotalValue(Long farmerId);

    @Query(nativeQuery = true, value = "SELECT SUM(value) FROM loan WHERE farmer_id = ?1 AND payment_mode = 'CASH'")
    Double getCashValue(Long farmerId);

    @Query(nativeQuery = true, value = "SELECT SUM(value) FROM loan WHERE farmer_id = ?1 AND payment_mode = 'PRODUCE'")
    Double getProduceValue(Long farmerId);

    @Query(nativeQuery = true, value = "SELECT SUM(value) FROM loan WHERE farmer_id = ?1 AND status = 3")
    Double getDebt(long id);
}
