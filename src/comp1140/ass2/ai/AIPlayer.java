package comp1140.ass2.ai;

import comp1140.ass2.gameobjects.Player;

public interface AIPlayer {
    double evaluate();
    String[] selectActionSequence();
    Player player();
}
