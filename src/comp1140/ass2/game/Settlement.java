package comp1140.ass2.game;

import java.util.Map;

public class Settlement extends Building {
    boolean upgradeable;
    public static Map<Resource, Integer> COST = Map.of(Resource.BRICK,1,
            Resource.LUMBER, 1, Resource.WOOL,1, Resource.GRAIN,1);
    public static int point = 1;

    public Settlement(boolean upgradeable) {
        this.upgradeable = upgradeable;
    }

    public Settlement(Player owner, boolean upgradeable) {
        super(owner);
        this.upgradeable = upgradeable;
    }

    public boolean isUpgradeable() {
        return upgradeable;
    }

    @Override
    public String toString() {
        return "Settlement{" +
                "isUpgradeable=" + upgradeable +
                ", cost=" + COST +
                ", point=" + point +
                '}' + getOwner();
    }

    @Override
    public Map<Resource, Integer> getCost() {
        return COST;
    }

}
