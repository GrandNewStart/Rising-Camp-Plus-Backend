package com.ade.learnspringframework.app06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class App06FindMax {

    public static void main(String[] args) {
        try(var context = new AnnotationConfigApplicationContext(App06FindMax.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            int max = context.getBean(BusinessCalculationService.class).findMax();
            System.out.println("MAX: " + max);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
