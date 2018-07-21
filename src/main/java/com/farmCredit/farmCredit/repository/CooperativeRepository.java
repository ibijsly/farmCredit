package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CooperativeRepository extends JpaRepository<Cooperative, Long> {
    public Cooperative findById(long id);
}
