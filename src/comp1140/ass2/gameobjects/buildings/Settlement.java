package comp1140.ass2.gameobjects.buildings;

import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.Player;

import java.util.Map;

public class Settlement extends Building {

    public static Map<Resource, Integer> COST = Map.of(Resource.BRICK,1,
            Resource.LUMBER, 1, Resource.WOOL,1, Resource.GRAIN,1);
    public static int POINTS = 1;
    private final boolean upgradeable;

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
                ", point=" + POINTS +
                '}' + getOwner();
    }

}
