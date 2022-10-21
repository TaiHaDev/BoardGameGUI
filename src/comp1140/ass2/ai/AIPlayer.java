package comp1140.ass2.ai;

import comp1140.ass2.gameobjects.Player;

public interface AIPlayer {
    String[] selectActionSequence(String boardState);
    double evaluate(String boardState);
    Player player();
}
