
package com.ade.learnspringframework.app01;
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
