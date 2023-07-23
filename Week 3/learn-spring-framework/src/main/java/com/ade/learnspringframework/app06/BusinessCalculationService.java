package com.ade.learnspringframework.app06;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BusinessCalculationService {

    private DataService dataService;

    public BusinessCalculationService(DataService dataService) {
        super();
        System.out.println("Constructor: " + dataService);
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays
                .stream(dataService.retrieveData())
                .max()
                .orElse(0);

    }

}
