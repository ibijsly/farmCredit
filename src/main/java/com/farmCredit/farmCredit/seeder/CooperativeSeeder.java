package com.farmCredit.farmCredit.seeder;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.repository.CooperativeRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CooperativeSeeder {

    @Autowired
    CooperativeRepository cooperativeRepository;

    public void seed(){

        if (cooperativeRepository.count() > 8)
            return;

        Faker faker = new Faker();
        String states[] = {"OYO", "RIVERS", "KADUNA", "KOGI", "PLATEAU", "ENUGU"};

        for(int i = 0; i < 6; i++){

            for(int j = 0; j < 3; j++) {

                Cooperative cooperative = new Cooperative();

                cooperative.setName(faker.company().name() + " Cooperative");
                cooperative.setState(states[i]);

                cooperativeRepository.save(cooperative);
            }
        }
    }
}
