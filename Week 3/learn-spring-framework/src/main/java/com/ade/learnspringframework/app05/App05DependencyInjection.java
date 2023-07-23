package com.ade.learnspringframework.app05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
@ComponentScan
public class App05DependencyInjection {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(App05DependencyInjection.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(BusinessClass.class));
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
