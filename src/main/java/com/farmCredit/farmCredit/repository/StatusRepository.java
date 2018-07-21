package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByValue(String disbursed);

}
