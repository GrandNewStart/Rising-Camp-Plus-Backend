package com.ade.learnspringframework.app03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingBasic {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
            GameRunner gameRunner = context.getBean(GameRunner.class);
            gameRunner.run();;
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
