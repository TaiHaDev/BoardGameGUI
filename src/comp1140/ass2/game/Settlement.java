package comp1140.ass2.game;

public class Settlement implements Building {

    @Override
    public Resource[] getCost() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    public boolean isUpgradeable() {
        return false;
    }

}
