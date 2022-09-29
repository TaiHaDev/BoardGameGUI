package comp1140.ass2.builderstrategies;

import comp1140.ass2.buildings.Building;
import comp1140.ass2.buildings.Settlement;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

public record SettlementBuilder(GameInstance game, Player player) implements BuilderStrategy {
    @Override
    public void build(String input) {
        int position = Integer.parseInt(input.charAt(0) + "" + input.charAt(1));
        Building residential = game.getBoard().getResidentialBuilding().get(position);
        if (!(residential instanceof Settlement)) {
            residential = new Settlement(true);
        }
        residential.setOwner(player);
        game.getBoard().getResidentialBuilding().put(position, residential);
    }
}
