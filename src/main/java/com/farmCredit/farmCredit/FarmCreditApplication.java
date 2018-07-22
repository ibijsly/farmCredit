package com.farmCredit.farmCredit;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.repository.CooperativeRepository;
import com.farmCredit.farmCredit.repository.FarmerRepository;
import com.farmCredit.farmCredit.seeder.CooperativeSeeder;
import com.farmCredit.farmCredit.seeder.FarmerSeeder;
import com.farmCredit.farmCredit.seeder.LoanSeeder;
import com.farmCredit.farmCredit.seeder.StatusSeeder;
import com.farmCredit.farmCredit.utilities.ClassifierAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class FarmCreditApplication {

	@Autowired
	StatusSeeder statusSeeder;

	@Autowired
	CooperativeSeeder cooperativeSeeder;

	@Autowired
	FarmerSeeder farmerSeeder;

	@Autowired
	LoanSeeder loanSeeder;

	@Autowired
	CooperativeRepository cooperativeRepository;

	@Autowired
	FarmerRepository farmerRepository;

	@Autowired
	ClassifierAlgorithm classifierAlgorithm;

	public static void main(String[] args) {
		SpringApplication.run(FarmCreditApplication.class, args);
	}

//	@EventListener
//	public void seedDB(ContextRefreshedEvent refreshedEvent){
//
//		try {
//				statusSeeder.seed();
//				cooperativeSeeder.seed();
//
//				List<Cooperative> cooperativeList = cooperativeRepository.findAll();
//
//				for (Cooperative cooperative : cooperativeList)
//					farmerSeeder.seed(cooperative);
//
//				List<Farmer> farmerList = farmerRepository.findAll();
//
//				for (Farmer farmer : farmerList)
//					loanSeeder.seed(farmer);
//
////				classifierAlgorithm.classify();
//		}
//		catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}
}
