package com.farmCredit.farmCredit.service;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.Dataset;
import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.model.ResponseModel;
import com.farmCredit.farmCredit.repository.CooperativeRepository;
import com.farmCredit.farmCredit.repository.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {
    private final FarmerRepository farmerRepository;
    private final CooperativeRepository cooperativeRepository;
    private final LoanService loanService;

    public FarmerService(FarmerRepository farmerRepository, CooperativeRepository cooperativeRepository, LoanService loanService) {
        this.cooperativeRepository = cooperativeRepository;
        this.farmerRepository = farmerRepository;
        this.loanService = loanService;
    }

    public ResponseModel addFarmer(Farmer farmer){
        try {
            farmerRepository.save(farmer);
            return new ResponseModel("00", "Success", farmer);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel("99", "Error", farmer);
        }

    }

    public ResponseModel fetchById(long id){
        try {
            Farmer farmer = farmerRepository.findById(id);

            if (farmer == null)
                return new ResponseModel("02", "Not Found", farmer);
            return new ResponseModel("00", "Success", farmer);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel("99", "Error", null);
        }
    }

    public ResponseModel fetchByName(String name){
        try {
            List<Farmer> farmers = farmerRepository.findlikeName(name);

            if (farmers.size() <= 0)
                return new ResponseModel("02", "Not Found", farmers);
            return new ResponseModel("00", "Success", farmers);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel("99", "Error", null);
        }
    }

    public ResponseModel findByCooperativeId(long id){
        try {
            Cooperative cooperative = cooperativeRepository.findById(id);

            List<Farmer> farmers = farmerRepository.findByCooperativeId(cooperative);
            if (farmers.size() <= 0)
                return new ResponseModel("02", "Not Found", farmers);
            return new ResponseModel("00", "Success", farmers);

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel("99", "Error", null);
        }

    }


    public List<Farmer> fetchAllFarmers() {

        return farmerRepository.findlast20agent();
    }

    public Farmer findById(long id) {
        Farmer farmer = farmerRepository.findById(id);
        return  farmer;
    }

    public Dataset test(long id){
        Farmer farmer = farmerRepository.findById(id);
            if (farmer == null)
                return null;
            return loanService.getPerformance(farmer, "");

    }


}
