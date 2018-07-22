package com.farmCredit.farmCredit.repository;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    public Farmer findById(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM farmer WHERE fullname like '%?1%'")
    public List<Farmer> findlikeName(String name);

    public List<Farmer> findByCooperativeId(Cooperative cooperative);

    @Query(nativeQuery = true, value = "SELECT * FROM farmer ORDER BY ID DESC LIMIT 20")
    public List<Farmer> findlast20agent();
}
