package com.farmCredit.farmCredit.controller;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.service.CooperativeService;
import com.farmCredit.farmCredit.service.FarmerService;
import com.farmCredit.farmCredit.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/score")
public class CreditScoreController {

    private final FarmerService farmerService;
    private final CooperativeService cooperativeService;
    private final LoanService loanService;

    public CreditScoreController(FarmerService farmerService, CooperativeService cooperativeService, LoanService loanService) {
        this.farmerService = farmerService;
        this.cooperativeService = cooperativeService;
        this.loanService = loanService;
    }

    @GetMapping("/farmers")
    public ModelAndView viewFarmerCreditScore(@RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Farmer farmer = farmerService.findById(id);
        modelAndView.addObject("farmerFullName", farmer.getFullname());
        modelAndView.addObject("data",loanService.getPerformance(farmer,""));
        modelAndView.setViewName("profile");
        return modelAndView;

    }

    @GetMapping("/cooperative")
    public ModelAndView viewCooperativeCreditScore(@RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Cooperative cooperative = cooperativeService.findById(id);
        modelAndView.setViewName("profile");
        return modelAndView;

    }




}
