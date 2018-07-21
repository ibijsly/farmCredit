package com.farmCredit.farmCredit.seeder;

import com.farmCredit.farmCredit.model.Status;
import com.farmCredit.farmCredit.repository.StatusRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusSeeder {

    @Autowired
    StatusRepository statusRepository;

    public void seed(){

        Faker faker = new Faker();

        statusRepository.save(new Status("DISBURSED"));
        statusRepository.save(new Status("SETTLED"));
        statusRepository.save(new Status("DEFAULTING"));
    }
}
