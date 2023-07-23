
package com.ade.learnspringframework.app04;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SonicGameQualifier")
public class SonicGame implements GamingConsole {

    public void up() {
        System.out.println("Jump");
    }

    public void down() {
        System.out.println("Crouch");
    }

    public void left() {
        System.out.println("Run back");
    }

    public void right() {
        System.out.println("Run");
    }

}
