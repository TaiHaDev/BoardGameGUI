package comp1140.ass2.ai;

import comp1140.ass2.gameobjects.GameInstance;

public abstract class AIPlayer {

    private final GameInstance game;

    public AIPlayer(GameInstance game) {
        this.game = game;
    }

    abstract double evaluate();

    abstract String[] selectActionSequence();

    public GameInstance getGame() {
        return game;
    }

}
