package com.farmCredit.farmCredit.controller;

import com.farmCredit.farmCredit.service.DatasetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataset")
public class DatasetController {
    private final DatasetService datasetService;

    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @RequestMapping("/populate")
    public boolean populateDataset(){
        return datasetService.prepareData();
    }

    @RequestMapping("/test")
    public void test(){
        datasetService.test();
    }
}
