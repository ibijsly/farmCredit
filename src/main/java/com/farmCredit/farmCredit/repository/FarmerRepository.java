package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}
