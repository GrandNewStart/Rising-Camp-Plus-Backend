package com.ade.learnspringframework;

public class App01GamingBasic {
    public static void main(String[] args) {
        var sonicGame = new SonicGame();
        var marioGame = new MarioGame();
        var gameRunner = new GameRunner(marioGame);
        gameRunner.run();
    }

}
