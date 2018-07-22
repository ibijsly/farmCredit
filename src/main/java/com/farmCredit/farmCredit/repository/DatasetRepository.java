package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DatasetRepository extends JpaRepository<Dataset, Long> {
    @Query(nativeQuery = true, value = "SELECT * from dataset order by id desc limit 1 ")
    public Dataset findLastRecord();
}
