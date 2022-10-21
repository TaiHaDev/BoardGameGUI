package comp1140.ass2.ai;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.gameobjects.Player;
// author Matthew
public interface AIPlayer {

    double evaluate(String boardState);

    Player player();

    default String[] selectActionSequence(String boardState) {
        boolean seen = false;
        String[] best = null;
        for (String[] strings : CatanDiceExtra.generateAllPossibleActionSequences(boardState)) {
            if (!seen || evaluate(CatanDiceExtra.applyActionSequence(boardState, strings)) > evaluate(CatanDiceExtra.applyActionSequence(boardState, best))) {
                seen = true;
                best = strings;
            }
        }
        return seen ? best : new String[] {};
    }
}
