package com.ade.learnspringframework;

public class GameRunner {

    MarioGame marioGame;
    SonicGame sonicGame;

    public GameRunner(MarioGame game) {
        this.marioGame = game;
    }

    public GameRunner(SonicGame game) {
        this.sonicGame = game;
    }

    public void run() {
        if (marioGame != null) {
            System.out.println("Running game: " + marioGame);
            marioGame.up();
            marioGame.down();
            marioGame.left();
            marioGame.right();
            return;
        }
        if (sonicGame != null) {
            System.out.println("Running game: " + sonicGame);
            sonicGame.up();
            sonicGame.down();
            sonicGame.left();
            sonicGame.right();
        }
    }

}
