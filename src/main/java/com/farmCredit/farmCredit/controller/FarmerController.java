package com.farmCredit.farmCredit.controller;

import com.farmCredit.farmCredit.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FarmerController {

    @Autowired
    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    public FarmerService getFarmerService() {
        return farmerService;
    }

    @RequestMapping(value = "/viewfarmers",method = RequestMethod.GET)
    public ModelAndView viewFarmers(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("totalFarmers", farmerService.fetchAllFarmers());

        modelAndView.setViewName("farmerslist");

        return modelAndView;
    }
}
