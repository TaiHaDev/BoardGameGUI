package comp1140.ass2.gameobjects;

import comp1140.ass2.board.Board;
import comp1140.ass2.buildings.*;
import comp1140.ass2.handlers.BoardStateHandler;
import comp1140.ass2.handlers.ScoreStateHandler;
import comp1140.ass2.handlers.TurnStateHandler;
import comp1140.ass2.pipeline.Pipeline;
import comp1140.ass2.game.Resource;
import comp1140.ass2.helpers.CircularQueue;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameInstance {

    private final Board board = new Board();
    private int rollsDone;
    private int diceCount;
    private final CircularQueue<Player> players = new CircularQueue<>();
    private Map<Resource, Integer> diceResult = new HashMap<>();
    private Player longestRoad;
    private Player largestArmy;

    /**
     * Passes the {@param encodedString} through the board reader pipeline.
     * Once the first handler has read what it is interested in from the string,
     * it passes what it didn't use to the next handler. Thus, the individual
     * handler classes do not hardcode the order at which they are used.
     *
     * This solution means handlers can be added (even conditionally), removed, or
     * re-ordered later at will without having to modify the handler functions themselves.
     *
     * The aim here is to keep the board state reader open for extension, without
     * needing to worry about breaking already-working code.
     *
     * @param encodedString  the encoded string describing the board
     */
    public GameInstance(String encodedString) {
        Pipeline<String> stateReaderPipeline = new Pipeline<>(new TurnStateHandler(this))
                .addHandler(new BoardStateHandler(this))
                .addHandler(new ScoreStateHandler(this));

        stateReaderPipeline.execute(encodedString);
    }

    /**
     * Alternative constructor for easy test-writing.
     *
     * @param players  a list of players to initialise
     *                 the game with.
     */
    @Deprecated
    public GameInstance(Player[] players) {
        this.players.addAll(Arrays.asList(players));
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
        return resources.codePoints()
                .mapToObj(e -> Resource.decodeChar((char) e))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
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


    public void checkAndUpdateLongestRoad() {
        Player player = Board.hasLongestRoad(this);
        if (player == null) return;
        if (longestRoad == null) {
            player.setScore(player.getScore() + 2);
            longestRoad = player;
        } else if (!longestRoad.equals(player)) {
            longestRoad.setScore(longestRoad.getScore() - 2);
            player.setScore(player.getScore() + 2);
            longestRoad = player;
        }
    }

    public void checkAndUpdateLargestArmy() {
        Player player = Board.hasLargestArmy(this);
        if (player == null) return;
        if (largestArmy == null) {
            player.setScore(player.getScore() + 1);
            largestArmy = player;
        } else if (!largestArmy.equals(player)) {
            player.setScore(player.getScore() + 2);
            largestArmy.setScore(largestArmy.getScore() - 2);
            largestArmy = player;
        }
    }
    /**
     * mutate the diceResult map as it is used to build buildings.
     * this function will be used in buildAction classes.
     * @param buildingCost
     */
    public void useResources(Map<Resource, Integer> buildingCost) {
        if (buildingCost.equals(Castle.COST))
            diceResult.keySet()
                    .stream()
                    .filter(k -> diceResult.get(k) == 6)
                    .findFirst()
                    .ifPresent(resource -> diceResult.put(resource, 0));
        for (var entry : buildingCost.entrySet()) {
            diceResult.put(entry.getKey(), diceResult.get(entry.getKey()) - entry.getValue());
        }

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

    /**
     * Converts a GameInstance object to a valid encoded string.
     *
     * This is **only** to meet testing requirements, and converting GameInstances
     * to strings then back again is unsafe in general.
     *
     * @return the encoded string representing the current object
     */
    @Deprecated
    public String getAsEncodedString() {
        StringBuilder state = new StringBuilder()
                .append(this.getCurrentPlayer().getUniqueId())
                .append(this.getDiceCount())
                .append(this.getRollsDone())
                .append(this.getDiceResult().entrySet().stream()
                        .flatMap(entry -> Stream.generate(entry::getKey).limit(entry.getValue()))
                        .map(Resource::getId)
                        .map(String::valueOf)
                        .sorted()
                        .reduce("", (a, b) -> a + b));
        this.getPlayers().stream().sorted(Comparator.comparing(Player::getUniqueId)).forEach(player -> {
            state.append(player.getUniqueId());
            for (int i = 0; i < this.getBoard().getCastleBoard().length; i++) {
                if (player.equals(this.getBoard().getCastleBoard()[i].getOwner())) {
                    state.append("C").append(i);
                }
            }
            state.append(this.getBoard().getKnightBoard().entrySet().stream()
                    .filter(entry -> player.equals(entry.getValue().getOwner()))
                    .map(entry -> (entry.getValue().isJoker() ? "K" : "J") + (entry.getKey() < 10 ? "0" : "") + entry.getKey())
                    .sorted()
                    .reduce("", (a, b) -> a + b));
            Road[] roads = this.getBoard().getRoads();
            for (Road road : roads) {
                if (player.equals(road.getOwner())) {
                    state.append("R")
                            .append(road.getStart() < 10 ? 0 : "")
                            .append(road.getStart())
                            .append(road.getEnd() < 10 ? 0 : "")
                            .append(road.getEnd());
                }
            }
            state.append(this.getBoard().getResidentialBuilding().entrySet().stream()
                    .filter(entry -> player.equals(entry.getValue().getOwner()))
                    .map(entry -> (entry.getValue() instanceof Settlement ? "S" : "T") + (entry.getKey() < 10 ? "0" : "") + entry.getKey())
                    .sorted()
                    .reduce("", (a, b) -> a + b));
        });
        this.getPlayers().stream().sorted(Comparator.comparing(Player::getUniqueId)).forEach(player -> state.append(player.getUniqueId())
                .append(player.getScore() < 10 ? 0 : "")
                .append(player.getScore())
                .append(player.equals(this.getLongestRoad()) ? "R" : "")
                .append(player.equals(this.getLargestArmy()) ? "A" : ""));
        return state.toString();
    }

}