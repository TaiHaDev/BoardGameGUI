package comp1140.ass2.game;

import java.util.Map;

abstract class Building {

    private int point;
    private Player owner;

    public Building() {

    }

    public Building(Player owner) {
        this.owner = owner;
    }

    /**
     * Returns the resource cost of the current building.
     *
     * @return an array of resources that a given player would
     * be required to have rolled to successfully place this building.
     */
    public abstract Map<Resource, Integer> getCost();

    /**
     * Returns the number of points for the current building
     * to be added to the owning player's balance.
     *
     * @return the points given to a player by owning this
     */
    int getPoints() {
        return point;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }


}
