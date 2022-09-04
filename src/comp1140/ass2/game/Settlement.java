package comp1140.ass2.game;

import java.util.Map;

public class Settlement extends Building {
    boolean isUpgradeable;
    public static Map<Resource, Integer> cost = Map.of(Resource.BRICK,1,
            Resource.LUMBER, 1, Resource.WOOL,1, Resource.GRAIN,1);
    public static int point = 1;
    public Settlement( Player owner, boolean isUpgradeable) {
        super(owner);
        this.isUpgradeable  = isUpgradeable;
    }

    public boolean isUpgradeable() {
        return isUpgradeable;
    }

    @Override
    public String toString() {
        return "Settlement{" +
                "isUpgradeable=" + isUpgradeable +
                ", cost=" + cost +
                ", point=" + point +
                '}' + owner;
    }
}
