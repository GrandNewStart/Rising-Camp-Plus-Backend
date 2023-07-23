package com.ade.learnspringframework.app05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessClass {

//    Field Injection
//    @Autowired
//    Dependency1 dependency1;
//
//    @Autowired
//    Dependency2 dependency2;

// Setter Injection
//    @Autowired
//    public void setDependency1(Dependency1 dependency1) {
//        this.dependency1 = dependency1;
//    }
//
//    @Autowired
//    public void setDependency2(Dependency2 dependency2) {
//        this.dependency2 = dependency2;
//    }
//
//    Dependency1 dependency1;
//
//    Dependency2 dependency2;

//    Constructor Injection
    Dependency1 dependency1;
    Dependency2 dependency2;

    public BusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        super();
        System.out.println("Constructor Injection - BusinessClass");
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    public String toString() {
        return "Using: " + dependency1 + " and " + dependency2;
    }

}
