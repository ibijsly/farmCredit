package com.farmCredit.farmCredit.seeder;

import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.model.Loan;
import com.farmCredit.farmCredit.model.Status;
import com.farmCredit.farmCredit.repository.LoanRepository;
import com.farmCredit.farmCredit.repository.StatusRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

        if (loanRepository.count() > 10000)
            return;
        Faker faker = new Faker();

        HashMap<Integer, Status>  statuses = new HashMap<>();
        statuses.put(1, statusRepository.findByValue("DISBURSED"));
        statuses.put(2, statusRepository.findByValue("SETTLED"));
        statuses.put(3, statusRepository.findByValue("DEFAULTING"));

        Random random = new Random();

        int num = 10;

        for(int i = 0; i < num; i++){

            Loan loan = new Loan();

            loan.setFarmer(farmer);
            loan.setDuration(8);
            loan.setValue((Math.round(Math.random() + 1)/2 * 100000));
            loan.setPayable(loan.getValue() *  (Math.random()/3 + 1));

            int k = random.nextInt(3) + 1;
            loan.setStatus(statuses.get(k));

            if(k == 1){
                LocalDateTime ldt = LocalDateTime.now().minusMonths(random.nextInt(10) + 8);
                Instant instant = ldt.atZone(java.time.ZoneId.systemDefault()).toInstant();
                Date d = Date.from(instant);

                loan.setDateCreated(d);
                loan.setPaymentMode(Math.random() > 0.7 ? "CASH" : "PRODUCE");
            }
            else if (k == 2){
                LocalDateTime ldt = LocalDateTime.now().minusMonths(random.nextInt(10) + 20);
                Instant instant = ldt.atZone(java.time.ZoneId.systemDefault()).toInstant();
                Date d = Date.from(instant);
                LocalDateTime pdt = LocalDateTime.from(ldt);
                pdt.plusMonths(random.nextInt(3) + 6);
                Instant pInstant = pdt.atZone(java.time.ZoneId.systemDefault()).toInstant();
                Date p = Date.from(pInstant);

                loan.setDateCreated(d);
                loan.setPaybackDate(p);
                loan.setPaymentMode(Math.random() > 0.7 ? "CASH" : "PRODUCE");
            }
            else{
                LocalDateTime ldt = LocalDateTime.now().minusMonths(random.nextInt(10) + 20);
                Instant instant = ldt.atZone(java.time.ZoneId.systemDefault()).toInstant();
                Date d = Date.from(instant);
                LocalDateTime pdt = LocalDateTime.from(ldt);
                pdt.plusMonths(random.nextInt(9) + 6);
                Instant pInstant = pdt.atZone(java.time.ZoneId.systemDefault()).toInstant();
                Date p = Date.from(pInstant);

                loan.setDateCreated(d);
                loan.setPaybackDate(p);
                loan.setPaymentMode(Math.random() > 0.7 ? "CASH" : "PRODUCE");
            }

            loanRepository.save(loan);
        }
    }
}
