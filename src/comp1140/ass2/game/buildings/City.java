package comp1140.ass2.game.buildings;

import comp1140.ass2.game.board.Player;
import comp1140.ass2.game.helper.Resource;

import java.util.Map;

public class City extends Building {
    public static Map<Resource, Integer> COST = Map.of(Resource.ORE,3,
            Resource.GRAIN, 2);
    public static int POINTS = 2;

    public City(Player owner) {
        super(owner);
    }

}
