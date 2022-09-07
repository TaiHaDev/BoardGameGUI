package comp1140.ass2.game;

import javafx.scene.paint.Color;

import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameInstance {

    private final Board board = new Board();
    private int rollsDone;
    private int diceCount;
    private int round;
    public CircularQueue<Player> players = new CircularQueue<>();
    private Map<Resource, Integer> diceResult = new HashMap<>();

    public static void main(String[] args) {
        String boardState = "W63gmWC3J09K00K01K03K07K08K14R0003R0004R0104R0307R0408R0711R0712R0812R1116R1217R1621R1622R1722R1723R1823R2127R2228R2329R2733R2833R2834R2934R2935S00S01S07S16S17XK02K04K05K06K11K12R0105R0205R0206R0509R0610R0813R0913R0914R1014R1015R1318R1419R1520R1925R2025R2026R2531R2632R3136R3137R3237S02S09S20T10W11RAX05";
    }

    public GameInstance(String encodedString) {
        char turn = encodedString.charAt(0); // TODO add into class
        this.diceCount = Integer.parseInt(encodedString.substring(1, 2));
        this.rollsDone = Integer.parseInt(encodedString.substring(2, 3));
        HashMap<Resource, Integer> resources = new HashMap<>();
        int currentChar = 3;
        for (char c : encodedString.substring(currentChar).toCharArray()) {
            if (!Arrays.asList('b', 'g', 'l', 'm', 'o', 'w').contains(c)) break;
            Resource resource = switch (c) {
                case 'b' -> Resource.BRICK;
                case 'g' -> Resource.GRAIN;
                case 'l' -> Resource.LUMBER;
                case 'm' -> Resource.GOLD;
                case 'o' -> Resource.ORE;
                case 'w' -> Resource.WOOL;
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
            resources.put(resource, 1 + resources.getOrDefault(resource, 0));
            currentChar++;
        }
        this.setDiceResult(resources);

        for (int i = 0; i < 2; i++) {
            char id = encodedString.charAt(currentChar++);
            Player player = new Player();
            player.setName(String.valueOf(id));
            players.add(player);

            while (true) {
                char c = encodedString.charAt(currentChar++);
                if (c == 'C') {
                    int position = Character.getNumericValue(encodedString.charAt(currentChar++));
                    Castle castle = new Castle(player);
                    this.getBoard().getCastleBoard()[position] = castle;
                } else if (c == 'J' || c == 'K') {
                    Knight knight = new Knight(player);
                    knight.setJoker(c == 'J');
                    // add one to the second parameter because in String#substring,
                    // lower bound is inclusive, while upper is exclusive.
                    int position = Integer.parseInt(encodedString.substring(currentChar++, 1 + currentChar++));
                    this.getBoard().getKnightBoard().put(position, knight);
                } else if (c == 'R') {
                    currentChar++;
                    currentChar++;
                    currentChar++;
                    currentChar++;
                } else if (c == 'S') {
                    currentChar++;
                    currentChar++;
                } else if (c == 'T') {
                    currentChar++;
                    currentChar++;
                } else {
                    currentChar--;
                    break;
                }
            }
        }

    }

    public void decodeStateString(String state, Player player) {
        String[] structureIdentifiers = state.split("(?=[A-Z])");
        for (var building : structureIdentifiers) {
            board.updateBoardUsingEncodedString(building, player);
        }
    }

    public static Map<Resource, Integer> stringResourcesToMap(String resources) {
        Map<Resource, Integer> finalMap = new HashMap<>();
        for (char e : resources.toCharArray()) {
            Resource resource = Resource.decodeChar(e);
            long countChar = resources.chars().filter(c -> c == e).count();
            if (!finalMap.containsKey(resource)) {
                finalMap.put(resource, (int) countChar);
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
        return players;
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
            outputString.append("Player ").append(player).append(" can roll ").append(diceCount).append(" dices ").append("with ").append
                            (3 - rollsDone).append(" re roll trial(s) left, ").append(" the current resources are ").
                    append(diceResult.toString()).append(" and posses properties: \n");
        } else {
            outputString.append("Player ").append(player).append(" have properties: \n");
        }
        for (var e : board.getCastleBoard()) {
            if (e.getOwner() != null && e.getOwner().equals(player)) {
                outputString.append("Castle number ").append(Arrays.asList(board.getCastleBoard()).indexOf(e)).append(", ");
            }
        }
        for (var e : board.getKnightBoard().entrySet()) {
            Knight knight = e.getValue();
            if (knight.getOwner() != null && knight.getOwner().equals(player)) {
                outputString.append("Knight number ").append(e.getKey()).append(", ");
            }
        }
        for (int i = 0; i < 54; i++) {
            for (GameGraph.Node node : board.getRoadBoard().adjacencyMatrix.get(i)) {
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
        }
        outputString.append("\n");
        outputString.append("With a total point of ").append(player.getScore()).append("\n\n\n");


        return outputString.toString();
    }

    public Board getBoard() {
        return board;
    }

    public void setDiceResult(Map<Resource, Integer> diceResult) {
        this.diceResult = diceResult;
    }
}
