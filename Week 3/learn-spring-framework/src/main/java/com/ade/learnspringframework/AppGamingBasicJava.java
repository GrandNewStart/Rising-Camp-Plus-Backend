package com.ade.learnspringframework;

public class AppGamingBasicJava {

    public static void main(String[] args) {
        var sonicGame = new SonicGame();
        var marioGame = new MarioGame();
        var gameRunner = new GameRunner(sonicGame);
        gameRunner.run();
    }

}
