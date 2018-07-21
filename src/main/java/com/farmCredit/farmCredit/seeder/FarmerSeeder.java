package com.farmCredit.farmCredit.seeder;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.repository.FarmerRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class FarmerSeeder {

    @Autowired
    FarmerRepository farmerRepository;

    public void seed(Cooperative cooperative){

        if(farmerRepository.count() > 90)
            return;

        Faker faker = new Faker();

        Random random = new Random();
        int num = random.nextInt(10) + 10;

        for(int i = 0; i < num; i++){

            Farmer farmer = new Farmer();
            farmer.setFullname(faker.name().fullName());
            farmer.setFarmName(faker.company().name());
            farmer.setAverageOutput( (1 + Math.random()) / 2 * 15);
            farmer.setFarmSize((1 + Math.random()) / 2 * 15);
            farmer.setFarmLocation(cooperative.getState());
            farmer.setFarmState(cooperative.getState());
            farmer.setCooperativeId(cooperative);
            farmer.setRegDate(new Date());

            farmerRepository.save(farmer);
        }
    }
}
