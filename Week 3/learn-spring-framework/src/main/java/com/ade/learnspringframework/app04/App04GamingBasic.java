package com.ade.learnspringframework.app04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ade.learnspringframework.app04")
public class App04GamingBasic {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(App04GamingBasic.class)) {
            GameRunner gameRunner = context.getBean(GameRunner.class);
            gameRunner.run();
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
