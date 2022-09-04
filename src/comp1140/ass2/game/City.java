package comp1140.ass2.game;

import java.util.HashMap;
import java.util.Map;

public class City extends Building {
    public static Map<Resource, Integer> cost = Map.of(Resource.ORE,3,
            Resource.GRAIN, 2);
    int point = 2;
    public City(Player owner) {
        super(owner);
    }

    @Override
    public Resource[] getCost() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

}
