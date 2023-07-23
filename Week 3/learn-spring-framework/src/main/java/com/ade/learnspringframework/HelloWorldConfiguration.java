package com.ade.learnspringframework;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
    @Primary
    public Address address() {
        return new Address("Baker Street", "London");
    }

    @Bean
    @Qualifier("personWithQualifier")
    public Address address2() {
        return new Address("Songpa Avenue", "Seoul");
    }

    @Bean
    @Primary
    public Person person() {
        return new Person(name(), 20, address());
    }

    @Bean (name="person2")
    public Person personWithParams(String name, int age, @Qualifier("personWithQualifier") Address address) {
        return new Person(name, age, address);
    }


}
