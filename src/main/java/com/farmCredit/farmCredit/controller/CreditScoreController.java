package com.farmCredit.farmCredit.controller;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.service.CooperativeService;
import com.farmCredit.farmCredit.service.FarmerService;
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

    public CreditScoreController(FarmerService farmerService, CooperativeService cooperativeService) {
        this.farmerService = farmerService;
        this.cooperativeService = cooperativeService;
    }

    @GetMapping("/farmers")
    public ModelAndView viewFarmerCreditScore(@RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Farmer farmer = farmerService.findById(id);
        modelAndView.addObject("farmerFullName", farmer.getFullname());
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
