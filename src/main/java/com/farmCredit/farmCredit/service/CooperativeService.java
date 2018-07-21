package com.farmCredit.farmCredit.service;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.ResponseModel;
import com.farmCredit.farmCredit.repository.CooperativeRepository;
import org.springframework.stereotype.Service;

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


}
