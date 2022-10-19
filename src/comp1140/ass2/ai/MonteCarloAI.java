package comp1140.ass2.ai;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

public record MonteCarloAI(GameInstance game, Player player) implements AIPlayer {

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public String[] selectActionSequence() {
        CatanDiceExtra.generateAllPossibleActionSequences(game.getAsEncodedString());
        return new String[0];
    }

}
