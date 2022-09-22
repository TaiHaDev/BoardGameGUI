package comp1140.ass2.game;

import java.util.Map;

public class Castle extends OwnableBuilding {

    public static Map<Resource, Integer> COST = Map.of(Resource.ORE,6,
            Resource.GRAIN, 6,Resource.WOOL,6, Resource.BRICK,6,
            Resource.GOLD, 6, Resource.LUMBER, 6);
    public static int POINTS = 2;

    public Castle(Player owner) {
        super(owner);
    }

}
