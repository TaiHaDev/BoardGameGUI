package comp1140.ass2.ai;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.List;
import java.util.Map;

public class AIFighterUtil {

    public static void main(String[] args) throws InterruptedException {
        GameInstance game = new GameInstance("W00WXW00X00");
        AIPlayer guy1 = new GreedyAI(game.getPlayers().get(0));
        game.getPlayers().get(0).setDisplayName("Greedy AI");

        AIPlayer guy2 = new SmartAI(game.getPlayers().get(1));
        game.getPlayers().get(1).setDisplayName("Smart AI");

        while (game.getPlayers().stream().noneMatch(player -> player.getScore() >= 10)) {
            for (AIPlayer ai : new AIPlayer[] { guy1, guy2 }) {
                if (game.getCurrentPlayer().getUniqueId().equals(ai.player().getUniqueId())) {
                    String[] sequence;
                    if (game.getRollsDone() == 0) {
                        game.rollDice(game.getDiceCount());
                    }
                    while (game.getRollsDone() < 3 && game.getDiceCount() != 0) {
                        game.setRollsDone(game.getRollsDone() + 1);
                        sequence = ai.selectActionSequence(game.getAsEncodedString());
                        game.applyActionSequence(sequence);

                        if (sequence.length > 0 && sequence[0].startsWith("keep")) {
                            int sum = 0;
                            for (int roll : game.getDiceResult().values()) {
                                sum += roll;
                            }
                            Map<Resource, Integer> result = game.getDiceResult();
                            Map<Resource, Integer> reroll = GameInstance.stringResourcesToMap(CatanDiceExtra.rollDice(game.getDiceCount() - sum));
                            for (Resource resource : Resource.values()) {
                                int n = result.getOrDefault(resource, 0) + reroll.getOrDefault(resource, 0);
                                if (n > 0) result.put(resource, n);
                            }
                            game.setDiceResult(result);
                        }

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
                    game.setRollsDone(0);

                    for (Player player : game.getPlayers()) {
                        if (player.getScore() >= 10) {
                            System.out.println(player.getDisplayName() + " wins, with " + player.getScore() + " points!");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(game.getPlayers().stream().filter(player -> player.getScore() >= 0).map(Player::getUniqueId).findFirst().orElse("Nobody") + " won the game!");
    }

}
