package comp1140.ass2.game;

public interface Building {

    /**
     * Returns the resource cost of the current building.
     *
     * @return an array of resources that a given player would
     * be required to have rolled to successfully place this building.
     */
    Resource[] getCost();

    /**
     * Returns the number of points for the current building
     * to be added to the owning player's balance.
     *
     * @return the points given to a player by owning this
     */
    int getPoints();
    
}
