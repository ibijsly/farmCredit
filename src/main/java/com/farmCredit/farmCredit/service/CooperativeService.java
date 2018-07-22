package com.farmCredit.farmCredit.service;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.ResponseModel;
import com.farmCredit.farmCredit.repository.CooperativeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CooperativeService {
    private final CooperativeRepository cooperativeRepository;

    public CooperativeService(CooperativeRepository cooperativeRepository) {
        this.cooperativeRepository = cooperativeRepository;
    }

    public ResponseModel add(Cooperative cooperative){
        try {
            cooperativeRepository.save(cooperative);
            return new ResponseModel("00", "Success", cooperative);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel("99", "Error", cooperative);
        }
    }

    public boolean addCooperative(Cooperative cooperative){
        Cooperative cooperative1 = this.checkCoopreativeName(cooperative.getName());
        System.out.println(cooperative.getName());
        if(cooperative1 == null){
            try{
                cooperative.setDateCreated(new Date());
                cooperativeRepository.save(cooperative);
                return true;

            }catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
        }
        else{
            return false;
        }


    }

    public Cooperative checkCoopreativeName(String name){
        return cooperativeRepository.findByName(name);
    }

    public List<Cooperative> fetchAllCooperatives() {
        return cooperativeRepository.findAll();
    }

    public Cooperative findById(long id) {
        Cooperative cooperative = cooperativeRepository.findById(id);
        return  cooperative;
    }
}
