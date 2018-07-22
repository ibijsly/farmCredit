package com.farmCredit.farmCredit.controller;

import com.farmCredit.farmCredit.model.Cooperative;
import com.farmCredit.farmCredit.service.CooperativeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class CooperativeController {


    private final CooperativeService cooperativeService;

    public CooperativeController(CooperativeService cooperativeService) {
        this.cooperativeService = cooperativeService;
    }

    @GetMapping(value = "/createcooperative")
    public ModelAndView registerCooperative(){
        ModelAndView modelAndView = new ModelAndView();
        Cooperative cooperative = new Cooperative();
        modelAndView.addObject("cooperativeForm", cooperative);
        modelAndView.setViewName("createcooperative");
        return modelAndView;
    }

    /**
     * Cooperative registration method
     * */
    @RequestMapping(value = "/createcooperative", method = RequestMethod.POST)
    public ModelAndView registerAgent(ModelAndView modelAndView, @Valid @ModelAttribute("cooperativeForm") Cooperative cooperativeForm, BindingResult bindingResult) {

        System.out.println("I entered controller");
        System.out.println(cooperativeForm.getName());

        if (bindingResult.hasErrors()) {
            System.out.println("I entered binding result");

            modelAndView.addObject("errorMessage", "check fields make sure all fields are correct");
            modelAndView.setViewName("createcooperative");
            return modelAndView;
        } else {

            if (cooperativeService.checkCoopreativeName(cooperativeForm.getName()) == null) {
                System.out.println("value is null");
                cooperativeService.addCooperative(cooperativeForm);
                modelAndView.addObject("success", "Cooperative Has Been registered succesfully");
//                modelAndView.addObject("cooperativeForm", new Cooperative());
                modelAndView.setViewName("cooplist");
                return modelAndView;

            }

            System.out.println("I got here!!!!");
            modelAndView.addObject("alreadyExist", " Cooperative with similar name exist");
            modelAndView.setViewName("createcooperative");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/viewcooperative",method = RequestMethod.GET)
    public ModelAndView viewCooperative() {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(cooperativeService.fetchAllCooperatives());

        modelAndView.addObject("totalcooperative", cooperativeService.fetchAllCooperatives());
        System.out.println(cooperativeService.fetchAllCooperatives().size());

        modelAndView.setViewName("cooplist");

        return modelAndView;
    }

    @GetMapping("/all")
    public @ResponseBody List<Cooperative> all(){
        return cooperativeService.fetchAllCooperatives();
    }

}
