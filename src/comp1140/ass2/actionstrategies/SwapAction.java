package comp1140.ass2.actionstrategies;

import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.Map;

public record SwapAction(GameInstance game, Player player) implements ActionStrategy {

    @Override
    public boolean isApplicable(String argument) {
        assert argument.length() == 2;
        Resource out = Resource.decodeChar(argument.charAt(0));
        Resource in = Resource.decodeChar(argument.charAt(1));
        if (GameInstance.isResourcesSufficient(game.getDiceResult(), Map.of(out, 1))) {
            return game.getBoard().isKnightResourceAvailable(in, player);
        }
        return false;
    }

    @Override
    public void apply(String argument) {
        Resource out = Resource.decodeChar(argument.charAt(0));
        Resource in = Resource.decodeChar(argument.charAt(1));
        game.getDiceResult().put(out, game.getDiceResult().get(out) - 1);
        game.getDiceResult().put(in, game.getDiceResult().getOrDefault(in, 0) + 1);
    }
}
