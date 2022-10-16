package comp1140.ass2.ai;

import comp1140.ass2.gameobjects.GameInstance;

public class BasicAI extends AIPlayer {

    public BasicAI(GameInstance game) {
        super(game);
    }

    @Override
    double evaluate() {
        return 0;
    }

    @Override
    String[] selectActionSequence() {
        return new String[0];
    }

}
