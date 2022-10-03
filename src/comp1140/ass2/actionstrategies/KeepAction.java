package comp1140.ass2.actionstrategies;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.Map;

public record KeepAction(GameInstance game, Player player) implements ActionStrategy {

    @Override
    public boolean isApplicable(String argument) {
        boolean isRollPhase = game.getRollsDone() != 0 && game.getRollsDone() != 3;
        return isRollPhase && GameInstance.isResourcesSufficient(game.getDiceResult(), GameInstance.stringResourcesToMap(argument));
    }

    @Override
    public void apply(String argument) {
        Map<Resource, Integer> resources = game.getDiceResult();
        Map<Resource, Integer> keeping = GameInstance.stringResourcesToMap(argument);
        int rolling = 0;
        for (Resource resource : resources.keySet()) {
            rolling += resources.get(resource) - keeping.getOrDefault(resource, 0);
        }
        for (Map.Entry<Resource, Integer> entry : GameInstance.stringResourcesToMap(CatanDiceExtra.rollDice(rolling)).entrySet()) {
            keeping.put(entry.getKey(), keeping.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        game.setDiceResult(keeping);
    }
}
