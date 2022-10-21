package comp1140.ass2.ai;

import comp1140.ass2.buildings.Knight;
import comp1140.ass2.buildings.Road;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

public record GreedyAI(Player player) implements AIPlayer {

    public double evaluate(String boardState) {
        double eval = 0;
        GameInstance game = new GameInstance(boardState);
        for (Road road : game.getBoard().getRoads()) {
            if (road.getOwner() != null && player.getUniqueId().equals(road.getOwner().getUniqueId())) {
                ++eval;
            }
        }
        for (Knight knight : game.getBoard().getKnightBoard().values()) {
            if (knight.getOwner() != null && player.getUniqueId().equals(knight.getOwner().getUniqueId())) {
                eval += 2;
            }
        }
        for (Player player : game.getPlayers()) {
            if (player.getUniqueId().equals(game.getCurrentPlayer().getUniqueId())) {
                eval += game.getCurrentPlayer().getScore();
                break;
            }
        }
        return eval;
    }

}
