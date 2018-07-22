package com.farmCredit.farmCredit.controller;

import com.farmCredit.farmCredit.utilities.ClassifierAlgorithm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classify")
public class ClassificationController {
    private final ClassifierAlgorithm classifierAlgorithm;

    public ClassificationController(ClassifierAlgorithm classifierAlgorithm) {
        this.classifierAlgorithm = classifierAlgorithm;
    }

    @RequestMapping("/train")
    public void train(){
        classifierAlgorithm.train();
    }
}
