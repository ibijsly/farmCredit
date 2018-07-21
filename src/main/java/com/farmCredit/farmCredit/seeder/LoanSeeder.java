package com.farmCredit.farmCredit.seeder;

import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.model.Loan;
import com.farmCredit.farmCredit.model.Status;
import com.farmCredit.farmCredit.repository.LoanRepository;
import com.farmCredit.farmCredit.repository.StatusRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@Service
public class LoanSeeder {
    
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    StatusRepository statusRepository;
    
    public void seed(Farmer farmer){

        Faker faker = new Faker();

        HashMap<Integer, Status>  statuses = new HashMap<>();
        statuses.put(1, statusRepository.findByValue("DISBURSED"));
        statuses.put(2, statusRepository.findByValue("SETTLED"));
        statuses.put(3, statusRepository.findByValue("DEFAULTING"));

        Random random = new Random();

        int num = 0;

        for(int i = 0; i < num; i++){

            Loan loan = new Loan();

            loan.setFarmer(farmer);
            loan.setDuration(8);
            loan.setValue((Math.round(Math.random() + 1)/2 * 100000));
            loan.setPayable(loan.getValue() *  (Math.random()/3 + 1));

            int k = random.nextInt(3) + 1;
            loan.setStatus(statuses.get(k));

            if(k == 1){
                loan.setDateCreated(new Date(LocalDateTime.now().minusMonths(random.nextInt(8)).toString()));
            }
            else if (k == 2){
                Date d = new Date(LocalDateTime.now().minusMonths(random.nextInt(10) + 20).toString());
                loan.setDateCreated(d);
                loan.setDateCreated(new Date(d.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().minusMonths(-1 * random.nextInt(9)).toString()));
            }
            else{
                Date d = new Date(LocalDateTime.now().minusMonths(random.nextInt(10) + 20).toString());
                loan.setDateCreated(d);
                loan.setDateCreated(new Date(d.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().minusMonths(-1 * random.nextInt(10) + 8).toString()));
            }

            loanRepository.save(loan);
        }
    }
}
