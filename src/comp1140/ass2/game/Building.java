package comp1140.ass2.game;

import javafx.scene.paint.Color;

import java.util.Date;

abstract class Building {
    static public Resource[] cost;
    static public int point;
    public Player owner;
    public Building( Player owner) {
        this.owner = owner;
    }
    /**
     * Returns the resource cost of the current building.
     *
     * @return an array of resources that a given player would
     * be required to have rolled to successfully place this building.
     */
    Resource[] getCost() {
        return cost;
    }

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
