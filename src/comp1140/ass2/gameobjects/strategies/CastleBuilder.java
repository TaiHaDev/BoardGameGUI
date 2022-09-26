package comp1140.ass2.gameobjects.strategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

public record CastleBuilder(GameInstance game, Player player) implements BuilderStrategy {
    @Override
    public void build(String input) {
        int position = Integer.parseInt(input.charAt(0) + "" + input.charAt(1));
        game.getBoard().getCastleBoard()[position].setOwner(player);
    }
}
