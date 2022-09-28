package comp1140.ass2.strategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.buildings.Building;

public record ResidentialBuilder(GameInstance game, Player player) implements BuilderStrategy {
    @Override
    public void build(String input) {
        int position = Integer.parseInt(input.charAt(0) + "" + input.charAt(1));
        Building residential = game.getBoard().getResidentialBuilding().get(position);
        residential.setOwner(player);
        game.getBoard().getResidentialBuilding().put(position, residential);
    }
}
