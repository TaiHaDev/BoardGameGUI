package comp1140.ass2.game;

import java.util.*;

public class GameInstance {
    public Board board = new Board();
    private int rollsDone;
    private int diceCount;
    private int round;
    public Deque<Player> players = new LinkedList<>();
    private Map<Resource, Integer> diceResult = new HashMap<>();


    public static void main(String[] args) {
        String boardState = "W63gmWC3J09K00K01K03K07K08K14R0003R0004R0104R0307R0408R0711R0712R0812R1116R1217R1621R1622R1722R1723R1823R2127R2228R2329R2733R2833R2834R2934R2935S00S01S07S16S17XK02K04K05K06K11K12R0105R0205R0206R0509R0610R0813R0913R0914R1014R1015R1318R1419R1520R1925R2025R2026R2531R2632R3136R3137R3237S02S09S20T10W11RAX05";


    }
    public GameInstance(String encodedString) {
        String[] parts = splitEncodedString(encodedString);
        String turn = parts[0];
        String resources = turn.substring(2, turn.length());
        String score = parts[2];
        int xIndex = score.indexOf('X');
        String wScoreString = score.substring(0, xIndex);
        String xScoreString = score.substring(xIndex, score.length());
        int wScore = Integer.parseInt(wScoreString.substring(0,2));
        int xScore = Integer.parseInt(wScoreString.substring(0,2));
        this.diceCount = Character.getNumericValue(turn.charAt(0));
        this.rollsDone = Character.getNumericValue(turn.charAt(1));
        String currentPlayer = encodedString.substring(0,1);
        Player x = new Player("X", null, null, xScore);
        Player w = new Player("W", null, null, wScore);
        if (currentPlayer.equals(x.getName())) {
            this.players.addLast(x);
            this.players.addLast(w);
        } else {
            this.players.addLast(w);
            this.players.addLast(x);
        }
        this.diceResult = stringResourcesToMap(resources);

        String playerBoardState = parts[1];
        String firstState = playerBoardState.substring(0, playerBoardState.indexOf("X"));
        String secondState = playerBoardState.substring(playerBoardState.indexOf("X") + 1, playerBoardState.length());
        if (!firstState.isEmpty())
            decodeStateString(firstState,w);
        if (!secondState.isEmpty())
            decodeStateString(secondState, x);
    }

    public static String[] splitEncodedString(String encodedString) {
        return encodedString.substring(1, encodedString.length()).split("W");
    }
    public void decodeStateString(String state, Player player) {
        String[] structureIdentifiers = state.split("(?=[A-Z])");
        for (var building : structureIdentifiers) {
            board.updateBoardUsingEncodedString(building, player);
        }
    }
    public static Map<Resource, Integer> stringResourcesToMap(String resources) {
        Map<Resource, Integer> finalMap = new HashMap<>();
        for (var e : resources.toCharArray()) {
            Resource resource = Resource.decodeChar(e);
            Long countChar = resources.chars().filter(c -> c == e).count();
            if (!finalMap.containsKey(resource)) {
                finalMap.put(resource, countChar.intValue());
            }
        }
        return finalMap;
    }

    public static boolean isResourcesSufficient(Map<Resource, Integer> resources, Map<Resource, Integer> requirement)  {
        for (var entry : requirement.entrySet()) {
            if (resources.getOrDefault(entry.getKey(), -1) < entry.getValue()) {
                return false;
            } else if (resources.get(entry.getKey()) == 6 && entry.getValue() == 6) {
                return true;
            }
        }
        return true;
    }
    public Player getCurrentPlayer() {
        return players.getFirst();
    }

    public Player nextPlayer() {
        players.addLast(players.removeFirst());
        return players.getFirst();
    }
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

    public Map<Resource, Integer> getDiceResult() {
        return diceResult;
    }

    public int getRollsDone() {
        return rollsDone;
    }

    public String toString(Player player) {
        StringBuilder outputString = new StringBuilder();
        if (player.equals(getCurrentPlayer())) {
            outputString.append("Player ").append(player.toString()).append(" can roll ").append(diceCount).append(" dices ").append("with ").append
                            (Integer.toString(3 - rollsDone)).append(" re roll trial(s) left, ").append(" the current resources are ").
                    append(diceResult.toString()).append(" and posses properties: \n");
        } else {
            outputString.append("Player ").append(player.toString()).append(" have properties: \n");
        }
        for (var e : board.castleBoard) {
            if (e.getOwner() != null && e.getOwner().equals(player)) {
                outputString.append("Castle number ").append(Arrays.asList(board.castleBoard).indexOf(e)).append(", ");
            }
        }
        for (var e : board.knightBoard.entrySet()) {
            Knight knight = e.getValue();
            if (knight.getOwner() != null && knight.getOwner().equals(player)) {
                outputString.append("Knight number ").append(e.getKey()).append(", ");
            }
        }
        for (int i = 0; i < 54; i++) {
            for (GameGraph.Node node : board.roadBoard.adjacencyMatrix.get(i)) {
                if (node.player != null &&  node.player.equals(player) && node.location > i) {
                    outputString.append(" Road number " + i  + node.location).append(", ");
                }
            }
        } for (var entry : board.residentialBuilding.entrySet()) {
            Building building = entry.getValue();
            Player owner = building.getOwner();
            if (building instanceof Settlement) {
                if (owner != null && owner.equals(player)){
                    outputString.append("Settlement number " + entry.getKey()).append(", ");
                }
            } else {
                if (owner != null && owner.equals(player)) {
                    outputString.append("House number " + entry.getKey()).append(", ");
                }
            }
            ;
        }
        outputString.append("\n");
        outputString.append("With a total point of ").append(player.getScore()).append("\n\n\n");


        return outputString.toString();
    }
}
