package comp1140.ass2.ai;

import comp1140.ass2.gameobjects.GameInstance;

public class SmartAI extends AIPlayer {

    public SmartAI(GameInstance game) {
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
