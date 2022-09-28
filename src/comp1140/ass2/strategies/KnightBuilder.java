package comp1140.ass2.strategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.buildings.Knight;

public record KnightBuilder(GameInstance game, Player player) implements BuilderStrategy {
    @Override
    public void build(String input) {
        int position = Integer.parseInt(input.charAt(0) + "" + input.charAt(1));
        Knight knight = game.getBoard().getKnightBoard().get(position);
        knight.setOwner(player);
        game.getBoard().getKnightBoard().put(position, knight);
    }
}
