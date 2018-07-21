package com.farmCredit.farmCredit.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @RequestMapping( value = {"/", "/farmcredit"}, method = GET)
    public ModelAndView landingPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Invalid UserName or Password, Please Check");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/signup")
    public ModelAndView signupPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Invalid UserName or Password, Please Check");
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @GetMapping(value = "/dashboard")
    public ModelAndView dashboard(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Invalid UserName or Password, Please Check");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @GetMapping(value = "/createfarmer")
    public ModelAndView registerFarmer(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Invalid UserName or Password, Please Check");
        modelAndView.setViewName("farmer");
        return modelAndView;
    }

    @GetMapping(value = "/createcooperative")
    public ModelAndView registerCooperative(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Invalid UserName or Password, Please Check");
        modelAndView.setViewName("createcooperative");
        return modelAndView;
    }

    @GetMapping(value = "/history")
    public ModelAndView history(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Invalid UserName or Password, Please Check");
        modelAndView.setViewName("history");
        return modelAndView;
    }



}
