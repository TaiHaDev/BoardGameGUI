package comp1140.ass2.ai;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.Arrays;
import java.util.List;

public class AIFighterUtil {

    public static void main(String[] args) throws InterruptedException {
        GameInstance game = new GameInstance("W00WXW00X00");
        AIPlayer guy1 = new GreedyAI(game, game.getPlayers().get(0));
        AIPlayer guy2 = new SmartAI(game, game.getPlayers().get(1));

        while (game.getPlayers().stream().noneMatch(player -> player.getScore() >= 10)) {
            for (AIPlayer ai : new AIPlayer[] { guy1, guy2 }) {
                if (game.getCurrentPlayer().getUniqueId().equals(ai.player().getUniqueId())) {
                    String[] sequence;
                    do {
                        sequence = ai.selectActionSequence(game.getAsEncodedString());
                        game.applyActionSequence(sequence);
                        game.completeTurn(false);
                    } while (Arrays.stream(sequence).anyMatch(action -> action.startsWith("keep")));
                    game.nextPlayer();
                    System.out.println("Player " + game.getCurrentPlayer().getUniqueId() + " chose " + List.of(sequence));
                }
            }
        }
        System.out.println(game.getPlayers().stream().filter(player -> player.getScore() >= 0).map(Player::getUniqueId).findFirst().orElse("Nobody") + " won the game!");
    }

}
