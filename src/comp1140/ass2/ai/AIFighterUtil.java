package comp1140.ass2.ai;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.List;

public class AIFighterUtil {

    public static void main(String[] args) throws InterruptedException {
        GameInstance game = new GameInstance("W00WXYW00X00Y00");
        AIPlayer guy1 = new SmartNoExpAI(game.getPlayers().get(0));
        game.getPlayers().get(0).setDisplayName("Semi-Smart AI");

        AIPlayer guy2 = new SmartAI(game.getPlayers().get(1));
        game.getPlayers().get(1).setDisplayName("Smart AI");

        AIPlayer guy3 = new GreedyAI(game.getPlayers().get(2));
        game.getPlayers().get(2).setDisplayName("Greedy AI");

        while (game.getPlayers().stream().noneMatch(player -> player.getScore() >= 10)) {
            AIFight(game, guy1, guy2, guy3);
            for (Player player : game.getPlayers()) {
                if (player.getScore() >= 10) {
                    System.out.println(player.getDisplayName() + " wins, with " + player.getScore() + " points!");
                    return;
                }
            }
        }
        System.out.println(game.getPlayers().stream().filter(player -> player.getScore() >= 0).map(Player::getUniqueId).findFirst().orElse("Nobody") + " won the game!");
    }

    public static void AIFight(GameInstance game, AIPlayer... players) {
        for (AIPlayer ai : players) {
            if (game.getCurrentPlayer().getUniqueId().equals(ai.player().getUniqueId())) {
                String[] sequence;
                while (game.getRollsDone() < 3 && game.getDiceCount() != 0) {
                    game.setRollsDone(game.getRollsDone() + 1);
                    game.rollDice(game.getDiceCount());
                    sequence = ai.selectActionSequence(game.getAsEncodedString());
                    game.applyActionSequence(sequence);
                    System.out.println("Player " + game.getCurrentPlayer().getUniqueId() + " chose " + List.of(sequence));
                }
                sequence = ai.selectActionSequence(game.getAsEncodedString());
                game.applyActionSequence(sequence);
                System.out.println("Player " + game.getCurrentPlayer().getUniqueId() + " chose " + List.of(sequence) +
                        " - now with " + game.getCurrentPlayer().getScore() + " points.");

                game.nextPlayer();
                if (game.getDiceCount() != 0 || game.getCurrentPlayer().getUniqueId().equals("W")) {
                    game.setDiceCount(Math.max(3, Math.min(game.getDiceCount() + 1, 6)));
                }
                game.setRollsDone(1);

            }
        }
    }

}
