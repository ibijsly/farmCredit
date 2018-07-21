package com.farmCredit.farmCredit.controller;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.service.CooperativeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class CooperativeController {


    private final CooperativeService cooperativeService;

    public CooperativeController(CooperativeService cooperativeService) {
        this.cooperativeService = cooperativeService;
    }

    /**
     * Agent registration method
     * */
    @RequestMapping(value = "/createcooperative", method = RequestMethod.POST)
    public ModelAndView registerAgent(ModelAndView modelAndView, @Valid @ModelAttribute("cooperativeForm") Cooperative cooperativeForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", "check fields make sure all fields are correct");
            modelAndView.setViewName("createcooperative");
            return modelAndView;
        } else {

            if(cooperativeService.checkCoopreativeName(cooperativeForm.getName()) != null){
                cooperativeService.addCooperative(cooperativeForm);
                modelAndView.addObject("success", "Cooperative Has Been registered succesfully");
                modelAndView.addObject("agentForm", new Cooperative());
                modelAndView.setViewName("viewCooperative");

            }
            modelAndView.addObject("alreadyExist", " Cooperative with similar name exist");
            modelAndView.setViewName("createcooperative");
            return modelAndView;
        }
    }
}
