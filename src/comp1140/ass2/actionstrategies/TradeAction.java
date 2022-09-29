package comp1140.ass2.actionstrategies;

import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.Map;

public record TradeAction(GameInstance game, Player player) implements ActionStrategy {

    @Override
    public boolean isApplicable(String argument) {
        Map<Resource, Integer> payResources = Map.of(Resource.GOLD, 2 * argument.length());
        return GameInstance.isResourcesSufficient(game.getDiceResult(), payResources);
    }

    @Override
    public void apply(String argument) {

    }
}
