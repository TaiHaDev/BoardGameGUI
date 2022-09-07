package comp1140.ass2.game;

import java.util.Map;

public class Castle extends Building {
    public static Map<Resource, Integer> cost = Map.of(Resource.ORE,6,
            Resource.GRAIN, 6,Resource.WOOL,6, Resource.BRICK,6,
            Resource.GOLD, 6, Resource.LUMBER, 6);
    public static int point = 2;
    public Castle(Player owner) {
        super(owner);
    }

    @Override
    public Map<Resource, Integer> getCost() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }


}
