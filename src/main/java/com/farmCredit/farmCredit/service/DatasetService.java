package com.farmCredit.farmCredit.service;

import com.farmCredit.farmCredit.model.Dataset;
import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.repository.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DatasetService {
    private final LoanService loanService;
    private final FarmerRepository farmerRepository;

    public DatasetService(LoanService loanService, FarmerRepository farmerRepository) {
        this.loanService = loanService;
        this.farmerRepository = farmerRepository;
    }

    public boolean prepareData(){
        try {
            List<Farmer> farmers = farmerRepository.findAll();

            for (Farmer farmer : farmers) {
                loanService.getPerformance(farmer, "dataset");
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
