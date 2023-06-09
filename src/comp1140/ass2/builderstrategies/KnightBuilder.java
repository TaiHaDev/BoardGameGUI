package comp1140.ass2.builderstrategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.buildings.Knight;
// Author Matthew
public record KnightBuilder(GameInstance game, Player player, boolean joker) implements BuilderStrategy {
    @Override
    public void build(String input) {
        int position = Integer.parseInt(input.charAt(0) + "" + input.charAt(1));
        Knight knight = game.getBoard().getKnightBoard().get(position);
        knight.setOwner(player);
        knight.setJoker(joker);
        game.getBoard().getKnightBoard().put(position, knight);
    }
}
