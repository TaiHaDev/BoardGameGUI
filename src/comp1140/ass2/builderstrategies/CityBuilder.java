package comp1140.ass2.builderstrategies;

import comp1140.ass2.buildings.Building;
import comp1140.ass2.buildings.City;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
// Author Matthew
public record CityBuilder(GameInstance game, Player player) implements BuilderStrategy {
    @Override
    public void build(String input) {
        int position = Integer.parseInt(input.charAt(0) + "" + input.charAt(1));
        Building residential = game.getBoard().getResidentialBuilding().get(position);
        if (!(residential instanceof City)) {
            residential = new City();
        }
        residential.setOwner(player);
        game.getBoard().getResidentialBuilding().put(position, residential);
    }
}
