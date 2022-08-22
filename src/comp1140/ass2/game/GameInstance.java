package comp1140.ass2.game;

import java.util.List;

public class GameInstance {
    private Hex[] board;
    private int round;
    private int diceCount;
    private CircularQueue<Player> players;
    private Resource[] diceResult;
    private Road[] roads;

    /**
     * The constructor should initialise the trivial
     * variables to assign (round, dice count),
     * and set up the board (roads) as per the game rules.
     *
     * The players are taken as an argument. It is
     * assumed that sorting has already taken place.
     *
     * @param players  the initialised players list as input
     *                 by users in the GUI
     */
    public GameInstance(CircularQueue<Player> players) {

    }

    /**
     * This method is run after every time
     * a road is claimed.
     *
     * This method returns the player
     * who has the most knights.
     * segment AND has at least three knights.
     *
     * It returns null if no players meet this
     * requirement.
     *
     * @return player
     */
    public Player getLargestArmy() {
        return null;
    }

    /**
     * This method is run after every time
     * a road is claimed.
     *
     * This method returns the player
     * who has the most road pieces in a continuous
     * segment AND has at least five roads.
     *
     * It returns null if no players meet this
     * requirement.
     *
     * @return player
     */
    public Player getLongestRoad() {
        return null;
    }

    public int getRound() {
        return 0;
    }

    public void setRound(int round) {

    }

    public int getDiceCount() {
        return 0;
    }

    public void setDiceCount(int diceCount) {

    }

    public CircularQueue<Player> getPlayers() {
        return null;
    }

    public void setPlayers(CircularQueue<Player> players) {

    }

    public Road[] getRoads() {
        return null;
    }

    public void setRoads(Road[] roads) {

    }

    public void tradeGold() {

    }

    /**
     * Generates a list of buildings that the given player can
     * build on their current turn.
     *
     * @param player  the player whose balance should be checked
     *                to see what they can afford
     * @return the buildings that can be affordably and physically
     * built by the current player.
     */
    public List<Building> availableToBuild(Player player) {
        return null;
    }

    /**
     * The player may choose to build at least one affordable
     * building on their turn. This method places these buildings
     * (iff they really are able to be afforded by the passed
     * player) onto the board.
     *
     * @param buildings  the buildings requested to be placed
     * @param player     the current player -- intended to be
     *                   passed from the head of the player queue
     */
    public void chooseToBuild(List<Building> buildings, Player player) {
        // modify board
    }

    /**
     * After a player rolls their die, they may wish to reroll
     * some. We provide this function to allow certain die to
     * be rerolled but not necessarily all.
     *
     * Example usage would be ``GameInstance#rollDice(1, 2, 5)``
     * to re-roll the first, second, and fifth die.
     *
     * @param indicesToReroll  the indices of the dice that the
     *                         player wants to be rerolled.
     */
    public void rollDice(int... indicesToReroll) {
    }

}
