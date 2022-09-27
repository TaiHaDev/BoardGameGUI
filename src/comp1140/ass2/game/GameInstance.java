package comp1140.ass2.game;

import java.util.*;

public class GameInstance {
    private final Board board = new Board();
    private int rollsDone;
    private int diceCount;
    public CircularQueue<Player> players = new CircularQueue<>();
    private Map<Resource, Integer> diceResult = new HashMap<>();
    private Player longestRoad;
    private Player largestArmy;
    public GameInstance(Player[] players) {
        this.players.addAll(Arrays.asList(players));
    }
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
                    int position = Integer.parseInt(encodedString.substring(currentChar++, ++currentChar));
                    Knight knight = this.getBoard().getKnightBoard().get(position);
                    knight.setOwner(player);
                    knight.setJoker(c!='J');
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
        for (int i = 0; i < players.size(); i++) {
            final String playerId = String.valueOf(encodedString.charAt(currentChar++));
            players.get(i).setScore(Integer.parseInt(String.valueOf(encodedString.charAt(currentChar++)) +
                    encodedString.charAt(currentChar++)));

            if (encodedString.length() > currentChar && encodedString.charAt(currentChar) == 'R') {
                Optional<Player> player = players.stream().filter(e -> e.getName().equals(playerId)).findFirst();
                player.ifPresent(this::setLongestRoad);
                currentChar++;
            }
            if (encodedString.length() > currentChar && encodedString.charAt(currentChar) == 'A') {
                Optional<Player> player = players.stream().filter(e -> e.getName().equals(playerId)).findFirst();
                player.ifPresent(this::setLargestArmy);
                currentChar++;
            }
        }

    }

    /**
     * Converts the resources section of a valid board
     * state to a map containing each present resource
     * given as the enum type {@link Resource} as the key,
     * and the number of those present in the string
     * as the integer value.
     *
     * Example: 'mloo' returns (as a Map):
     * {
     *     "GOLD": 1,
     *     "LUMBER": 1,
     *     "ORE": 2
     * }
     *
     * @param resources  from a vaild boardState
     * @return a map containing each resource and its
     *          count.
     */
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

    /**
     * Returns whether for every entry in {@param requirements},
     * the passed map {@param resources} has either the same amount
     * of, or more, of that resource.
     * The aim is to easily tell if a given player can 'afford' some
     * building.
     *
     * @param resources    what a player has available
     * @param requirement  what the player needs
     * @return true iff it is affordable
     */
    public static boolean isResourcesSufficient(Map<Resource, Integer> resources, Map<Resource, Integer> requirement)  {
        return requirement.keySet().stream()
                .allMatch(resource -> resources.containsKey(resource) &&
                        resources.get(resource) >= requirement.get(resource));
    }

    /**
     * Gets the topmost player from the players circular
     * queue.
     *
     * @return the current player to move
     */
    public Player getCurrentPlayer() {
        return players.peek();
    }

    /**
     * Moves the current player to the back of the queue,
     * and sets the current player to be the new first player
     * in line.
     *
     * @return the player who is second in the queue to move.
     */
    public Player nextPlayer() {
        return players.pop();
    }

    public int getDiceCount() {
        return diceCount;
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
        return null; // TODO
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
        // TODO
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
        // TODO
    }

    public Map<Resource, Integer> getDiceResult() {
        return diceResult;
    }

    public void setRollsDone(int rollsDone) {
        this.rollsDone = rollsDone;
    }

    public int getRollsDone() {
        return rollsDone;
    }

    public Board getBoard() {
        return board;
    }

    public void setDiceResult(Map<Resource, Integer> diceResult) {
        this.diceResult = diceResult;
    }

    public Player getLargestArmy() {
        return largestArmy;
    }

    public void setLargestArmy(Player largestArmy) {
        this.largestArmy = largestArmy;
    }

    public Player getLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(Player longestRoad) {
        this.longestRoad = longestRoad;
    }
}
