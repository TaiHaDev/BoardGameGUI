package comp1140.ass2.game;

import comp1140.ass2.game.board.Board;
import comp1140.ass2.game.board.Player;
import comp1140.ass2.game.buildings.*;
import comp1140.ass2.game.helper.CircularQueue;
import comp1140.ass2.game.helper.Resource;

import java.util.*;

public class GameInstance {

    private final Board board = new Board();
    private int rollsDone;
    private int diceCount;
    public CircularQueue<Player> players = new CircularQueue<>();
    private Map<Resource, Integer> diceResult = new HashMap<>();

    public GameInstance(String encodedString) {
        char turn = encodedString.charAt(0);
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
            Player player = new Player(String.valueOf(id));
            players.add(player);
            if (player.getName().equals(String.valueOf(turn))) {
                while (players.peek() != player) {
                    players.pop();
                }
            }

            while (true) {
                char c = encodedString.charAt(currentChar++);
                if (c == 'C') {
                    int position = Character.getNumericValue(encodedString.charAt(currentChar++));
                    Castle castle = new Castle(player);
                    this.getBoard().getCastleBoard()[position] = castle;
                } else if (c == 'J' || c == 'K') {
                    Knight knight = new Knight(player);
                    knight.setJoker(c == 'J');
                    // increment before current parameter because String#substring's
                    // upper bound is exclusive
                    int position = Integer.parseInt(encodedString.substring(currentChar++, ++currentChar));
                    this.getBoard().getKnightBoard().put(position, knight);
                } else if (c == 'R') {
                    int position1 = Integer.parseInt(encodedString.substring(currentChar++, ++currentChar));
                    int position2 = Integer.parseInt(encodedString.substring(currentChar++, ++currentChar));
                    for (int j = 0; j < this.getBoard().getRoads().length; j++) {
                        Road road = this.getBoard().getRoads()[j];
                        if (road == null) continue;
                        if ((road.getStart() == position1 && road.getEnd() == position2) ||
                                (road.getEnd() == position1 && road.getStart() == position2)) {
                            road.setOwner(player);
                            break;
                        }
                    }
                } else if (c == 'S') {
                    Settlement settlement = new Settlement(player, true);
                    int position = Integer.parseInt(encodedString.substring(currentChar++, ++currentChar));
                    this.getBoard().getResidentialBuilding().put(position, settlement);
                } else if (c == 'T') {
                    City city = new City(player);
                    int position = Integer.parseInt(encodedString.substring(currentChar++, ++currentChar));
                    this.getBoard().getResidentialBuilding().put(position, city);
                } else {
                    currentChar--;
                    break;
                }
            }
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
        return requirement.keySet().stream()
                .allMatch(resource -> resources.containsKey(resource) &&
                        resources.get(resource) >= requirement.get(resource));
    }

    public Player getCurrentPlayer() {
        return players.peek();
    }

    public Player nextPlayer() {
        return players.pop();
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

    public int getDiceCount() {
        return 0;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public CircularQueue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(CircularQueue<Player> players) {
        this.players = players;
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
//            for (Road road : board.getRoads()) {
//                if (player.equals(road.getOwner())) {
//                    outputString.append(" Road start ").append(i).append(road.getStart()).append(", ");
//                }
//            } FIXME
        } for (var entry : board.getResidentialBuilding().entrySet()) {
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
