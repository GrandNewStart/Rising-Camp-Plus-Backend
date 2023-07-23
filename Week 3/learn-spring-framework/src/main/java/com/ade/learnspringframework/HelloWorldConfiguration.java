package com.ade.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person (String name, int age, Address address) {}
record Address (String firstLine, String city) {}

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String name() {
        return "Ranga";
    }

    @Bean
    public int age() {
        return 15;
    }

    @Bean
    public Address address() {
        return new Address("Baker Street", "London");
    }

    @Bean
    public Person person() {
        return new Person(name(), 20, address());
    }

    @Bean (name="person2")
    public Person personWithParams(String name, int age, Address address) {
        return new Person(name, age, address);
    }


}
