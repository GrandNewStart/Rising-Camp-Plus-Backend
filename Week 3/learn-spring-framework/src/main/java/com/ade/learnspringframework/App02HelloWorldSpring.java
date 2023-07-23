package com.ade.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        // 01. Launch a Spring Context
        var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

        // 02. Configure the things that we want to be managed by Spring
        // HelloWorldConfiguration - @Configuration
        // name - @Bean

        // 03. Retrieving Beans managed by Spring
        System.out.println(context.getBean("name"));
        System.out.println(context.getBean("age"));
        System.out.println(context.getBean("address"));
        System.out.println(context.getBean("person"));
        System.out.println(context.getBean("person2"));
        System.out.println(context.getBean(Address.class));
        System.out.println(context.getBean(String.class));

        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }

}
