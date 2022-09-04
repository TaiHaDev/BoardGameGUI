package comp1140.ass2.game;

import java.util.Map;

public class Road extends Building {
    int source;
    int destination;

    boolean isCoastal;
    public static Map<Resource, Integer> cost = Map.of(Resource.BRICK,1,
            Resource.LUMBER, 1);
    public static int point = 0;
    public Road(Player owner, int source, int destination, boolean isCoastal) {
        super(owner);
        this.isCoastal = isCoastal;
        this.source = source;
        this.destination = destination;
    }

    public static boolean isValidRoad() {
        return false;
    }
}
