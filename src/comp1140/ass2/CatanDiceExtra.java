package comp1140.ass2;

import comp1140.ass2.game.*;
import comp1140.ass2.game.board.GameGraph;
import comp1140.ass2.game.board.Player;
import comp1140.ass2.game.buildings.*;
import comp1140.ass2.game.helper.Resource;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatanDiceExtra {


    /**
     * Check if the string encoding of a board state is well-formed.
     * Note that this does not mean checking if the state is valid
     * (represents a state that the player could get to in game play),
     * only that the string representation is syntactically well-formed.
     *
     * A description of the board state string will be provided in a
     * later update of the project README.
     *
     * @param boardState: The string representation of the board state.
     * @return true iff the string is a well-formed representation of
     * a board state, false otherwise.
     */
    public static boolean isBoardStateWellFormed(String boardState) {
        // FIXME: Task 3
//         turn section
        String regex1 = "^(W|X)([3-6][1-3]|00)([b,g,l,m,o,w]{0}|[b,g,l,m,o,w]{2,6})(W(C[0-4])*(J(0[0-9]|1[0-9]))*" +
                "(K(0[0-9]|1[0-9]))*(R(\\d\\d\\d\\d))*(S([0-4][0-9]|5[0-3]))*(T([0-4][0-9]|5[0-3]))*)(X(C[0-4])*" +
                "(J(0[0-9]|1[0-9]))*(K(0[0-9]|1[0-9]))*(R(\\d\\d\\d\\d))*(S([0-4][0-9]|5[0-3]))*(T([0-4][0-9]|5[0-3]))*)" +
                "(W(0[0-9]|1[0-1])R?A?X(0[0-9]|1[0-1]))?(W(0[0-9]|1[0-1])X(0[0-9]|1[0-1])R?A?)?(W(0[0-9]|1[0-1])" +
                "R?X(0[0-9]|1[0-1])A?)?(W(0[0-9]|1[0-1])A?X(0[0-9]|1[0-1])R?)?$";
        String regex2 = "^(W|X)([3-6][1-3]|00)(b*g*l*m*o*w*)(W(C[0-4])*(J(0[0-9]|1[0-9]))*(K(0[0-9]|1[0-9]))*" +
                "(R(\\d\\d\\d\\d))*(S([0-4][0-9]|5[0-3]))*(T([0-4][0-9]|5[0-3]))*)(X(C[0-4])*(J(0[0-9]|1[0-9]))*" +
                "(K(0[0-9]|1[0-9]))*(R(\\d\\d\\d\\d))*(S([0-4][0-9]|5[0-3]))*(T([0-4][0-9]|5[0-3]))*)" +
                "(W(0[0-9]|1[0-1])R?A?X(0[0-9]|1[0-1]))?(W(0[0-9]|1[0-1])X(0[0-9]|1[0-1])R?A?)?(W(0[0-9]|1[0-1])R?X" +
                "(0[0-9]|1[0-1])A?)?(W(0[0-9]|1[0-1])A?X(0[0-9]|1[0-1])R?)?$";
        Pattern p = Pattern.compile(regex1);
        Matcher m = p.matcher(boardState);
        boolean b = m.matches();
        Pattern p2 = Pattern.compile(regex2);
        Matcher m2 = p2.matcher(boardState);
        b = b && m2.matches();
        boolean roadValid = isValidRoad(boardState);
        return b && roadValid;
    }

    /**
     *
     * @param boardState
     * @return
     */
    public static boolean isValidRoad(String boardState) {
        boolean roadValid = true;
        long numberOfRoad = countRoad(boardState);
        for (int i = 0; i < numberOfRoad; i++) {
            if (boardState.contains("R")) {
                try {
                    int roadIndex = boardState.indexOf('R');
                    String roadEncoding = boardState.substring(roadIndex, roadIndex + 5);
                    boardState = boardState.replaceFirst(roadEncoding,"");
                    int firstPos = Integer.parseInt(roadEncoding.substring(1,3));
                    int secondPos = Integer.parseInt(roadEncoding.substring(3,5));
                    if (firstPos > secondPos || secondPos > 53) {
                        roadValid = false;
                    }
                } catch (Exception ignore) {
                    roadValid = false;
                }
            }
        }
        return roadValid;
    }

    public static int countRoad(String boardState) {
        boardState = boardState.replaceAll("[X,W]\\d\\dR", "");
        return (int) boardState.chars().filter(x -> x == 'R').count();
    }
    /**
     * Check if the string encoding of a player action is well-formed.
     * Note that this does not mean checking if the action is valid
     * (represents a player action that the player could get to in game play),
     * only that the string representation is syntactically well-formed.
     *
     * A description of the board state string will be provided in a
     * later update of the project README.
     *
     * @param action: The string representation of the action.
     * @return true iff the string is a well-formed representation of
     * a player action, false otherwise.
     */

    public static boolean isActionWellFormed(String action) {
        // FIXME: Task 4
	    String regex = "^(keep[b,g,l,m,o,w]{0,6}|build(R([0-4][0-9]|5[0-3]){2}|K(0[0-9]|1[0-9])|S([0-4][0-9]|5[0-3])|" +
                "T([0-4][0-9]|5[0-3])|C[0-4])|trade[b,g,l,m,o,w]{1,3}|swap[b,g,l,m,o,w][b,g,l,m,o,w])$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(action);
        boolean b = m.matches();
        if (b && action.length() >= 6 && action.substring(0,4).equals("keep")) {
            String resources = action.substring(4,action.length());
            b = isSorted(resources);

        } else if (b && action.length() == 10 && action.substring(0,6).equals("buildR")) {
            b = isValidRoad(action.substring(5,10));
        } else if (b && action.length() == 7 && action.substring(0,5).equals("trade")) {
            String resources = action.substring(5,7);
            b = isSorted(resources) && !resources.contains("m");
        }
        return b;
    }

    public static boolean isSorted(String str) {
        char[] charList = str.toCharArray();
        Arrays.sort(charList);
        return new String(charList).equals(str);
    }

    /**
     * Roll the specified number of *random* dice, and return the
     * rolled resources in string form.
     * The outcomes of dice rolls should be uniformly distributed.
     *
     * @param numOfDice the number of dices to roll
     * @return alphabetically ordered [Resources] with characters
     * 'b', 'l', 'w', 'g', 'o', 'm'.
     */
    public static String rollDice(int numOfDice) {
        // FIXME: Task 5
        char[] resources = new char[numOfDice];
        Random random = new Random();
        char[] resourcesList = new char[]{'b','l','w','g','o','m'};
        for (int i = 0; i < numOfDice; i++) {
            resources[i] = resourcesList[random.nextInt(resourcesList.length)];
        }
        Arrays.sort(resources);
        return new String(resources);
    }

    /**
     * Given a valid board state and player action, determine whether the
     * action can be executed.
     * The permitted actions depend on the current game phase:
     *
     * A. Roll Phase (keep action)
     * 1. A keep action is valid if it satisfies the following conditions:
     * - Action follows the correct format : "keep[Resources]", and the
     *   current player has the resources specified.
     * - [Rolls Done] is less than 3
     *
     *
     * B. Build Phase (build, trade, and swap actions)
     *
     * 1. A build action is valid if it satisfies the following conditions:
     * - Action follows the correct format : "build[Structure Identifier]"
     * - The current player has sufficient resources available for building
     *   the structure.
     * - The structure satisfies the build constraints (is connected to the
     *   current players road network).
     * - See details of the cost of buildable structure in README.md.
     *
     * 2. A trade action is valid if it satisfies the following conditions:
     * - Action follows the correct format : "trade[Resources]"
     * - The current player has sufficient resources available to pay for
     *   the trade.
     *
     * 3. A swap action is valid if it satisfies the following conditions:
     * - Action follows the correct format : "swap[Resource Out][Resource In]"
     * - The current player has sufficient resources available to swap out.
     * - The current player has an unused knight (resource joker) on the
     *   board which allows to swap for the desired resource.
     * @param boardState: string representation of the board state.
     * @param action: string representation of the player action.
     * @return true iff the action is executable, false otherwise.
     */
    public static boolean isActionValid(String boardState, String action) {
        if (!isActionWellFormed(action)) return false;
        GameInstance game = new GameInstance(boardState);
        Player player = game.getCurrentPlayer();
        Map<Resource, Integer> resourcesMap = game.getDiceResult();

        if (action.startsWith("keep")) {
            boolean isRollPhase = game.getRollsDone() != 0 && game.getRollsDone() != 3;
            return isRollPhase &&
                    GameInstance.isResourcesSufficient(resourcesMap, GameInstance.stringResourcesToMap(action.substring(4)));
        } else if (action.startsWith("build")) {
            String structureIdentifier = action.substring(5);
            if (structureIdentifier.length() < 3) return false;
            char typeOfBuilding = structureIdentifier.charAt(0);
            int location = Integer.parseInt(structureIdentifier.substring(1,3));
            if (typeOfBuilding == 'R') {
                int firstPos = Integer.parseInt(structureIdentifier.substring(1,3));
                int secondPos = Integer.parseInt(structureIdentifier.substring(3,5));
                boolean valid = false;
                if (game.getRollsDone() == 0) {
                    valid = game.getBoard().isCoastalAnd5RoadsAway(firstPos, secondPos, player) &&
                            game.getBoard().isRoadValid(firstPos, secondPos);
                } else if (game.getBoard().isRoadBuildable(firstPos, secondPos, player)) {
                    valid = GameInstance.isResourcesSufficient(resourcesMap, Road.COST);
                }
                return valid;
            } else if (typeOfBuilding == 'C') {
                location = Integer.parseInt(structureIdentifier.substring(1,2));
                if (game.getBoard().canCastleBuild(location)) {
                    return GameInstance.isResourcesSufficient(resourcesMap, Castle.cost);
                }
            } else if (typeOfBuilding == 'K') {
                if (game.getBoard().canKnightBuild(location, player )) {
                    return GameInstance.isResourcesSufficient(resourcesMap, Knight.COST);
                }
            } else if (typeOfBuilding == 'S') {
                if (game.getBoard().canSettlementBuild(location, player)) {
                    return GameInstance.isResourcesSufficient(resourcesMap, Settlement.COST);
                }
            } else if (typeOfBuilding == 'T') {
                if (game.getBoard().canCityBuild(location, player)) {
                    return GameInstance.isResourcesSufficient(resourcesMap, City.COST);
                }
            }

        } else if (action.contains("trade")) {
            String tradeList = action.substring(5);
            Map<Resource, Integer> payResources = Map.of(Resource.GOLD, 2 * tradeList.length());
            return GameInstance.isResourcesSufficient(resourcesMap, payResources);
        } else if (action.contains("swap")) {
            assert action.length() == 6;
            Resource out = Resource.decodeChar(action.charAt(4));
            Resource in = Resource.decodeChar(action.charAt(5));
            if (GameInstance.isResourcesSufficient(resourcesMap, Map.of(out, 1))) {
                if (game.getBoard().isKnightResourceAvailable(in, player))
                    return true;
            }
        }
        return false;
    }




    /**
     * Return an integer array containing the length of the longest contiguous
     * road owned by each player.
     * For example : given [Board State] =
     * "W61bgglwwWJ05K01K02R0105R0205R0206R0509R0610R0913R1015S02S09S10XK12R2026R2632R3137R3237R3742S37W07RAX01"
     * - Player 'W' has the longest road length of 6
     * - Player 'X' has the longest road length of 4
     * - The method should return {6, 4}
     * @param boardState: string representation of the board state.
     * @return array of contiguous road lengths, one per player.
     */
    public static int[] longestRoad(String boardState) {
        GameInstance game = new GameInstance(boardState);
        Map<Player, Integer> longestRoad = new HashMap<>();
        for (Player player : game.getPlayers()) {
            int[][] matrix = new int[GameGraph.VERTICES][GameGraph.VERTICES];
            for (Road road : game.getBoard().getRoads()) {
                if (player.equals(road.getOwner())) {
                    matrix[road.getStart()][road.getEnd()] = 1;
                    matrix[road.getEnd()][road.getStart()] = 1;
                }
            }
            Map<Integer, Integer> minimumCost = new HashMap<>();
        }
        return longestRoad.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getName()))
                .mapToInt(Map.Entry::getValue)
                .toArray();
    }

    /**
     * Return an integer array containing the size of the army owned by
     * each player.
     * For example : given [Board State] =
     * "W61bgglwwWJ05K01K02R0105R0205R0206R0509R0610R0913R1015S02S09S10XK12R2026R2632R3137R3237R3742S37W07RAX01"
     * - Player 'W' has an army of size 3
     * - Player 'X' has an army of size 1
     * - The method should return {3, 1}
     * @param boardState: string representation of the board state.
     * @return array of army sizes, one per player.
     */
    public static int[] largestArmy(String boardState) {
        GameInstance game = new GameInstance(boardState);
        return game.getPlayers().stream()
                .sorted(Comparator.comparing(Player::getName))
                .mapToInt(player ->
                        (int) game.getBoard().getKnightBoard().values().stream()
                                .filter(knight -> player.equals(knight.getOwner()))
                                .count()
                ).toArray();
    }

    /**
     * Given a valid board state and player action, this method should return
     * the next new board state that results from executing the action.
     * This method should both handle Start of the Game, Middle of the Game,
     * and Game End.
     *
     * A. Start of the Game
     * For example : given boardState = "W00WXW00X00", action = "buildR0205"
     * - Player 'W' builds a road from index 02 to 05
     * - The next boardState should be "X00WR0205XW00X00"
     * - Consult details of the rules for the Start of the Game in README.md
     *
     * B. Middle of the Game
     * For example : given boardState = "W61bbbgwwWR0205R0509S02XR3237W01X00", action = "keepbbbw"
     * - Player 'W' keeps three brick and one wool, and re-rolls two dice.
     * - The next boardState should be "W62[Next Resources]WR0205R0509S02XR3237W01X00"
     * - [Next Resources] can be any 6 resources that contain 3 bricks, 1 wool
     * - Some examples of [Next Resources] are "bbbbmw", "bbbglw", "bbblow", etc
     *
     * C. Game End
     * For example : given boardState = "X63lmoWK01K02K04K05K06R0105R0205R0206R0408R0509R0610R0812R0813R0913R0914R1014R1015R1318R1419R1520S01S02S08S09T10XJ09K10K11K12K15K16R1824R1924R1925R2025R2026R2430R2531R2632R3035R3036R3136R3137R3237R3641R3742R4145R4146R4246R4549S19S20S37S45T36W06X10RA"
     * - Player 'X' got the score 10 and game ended
     * - No action can be applied at this stage
     * @param boardState: string representation of the board state.
     * @param action: string representation of the player action.
     * @return string representation of the updated board state.
     */
    public static String applyAction(String boardState, String action) {
        // FIXME: Task 9
        return null;
    }

    /**
     * Given valid board state, this method checks if a sequence of player
     * actions is executable.
     * For example : given boardState = "W63bbglowWR0205R0509S02XR3237W01X00", actionSequence = {"buildK02","swapbo","buildR0105"}
     * - Player 'W' has resources available to build a knight at index 02 using 1 ore, 1 wool and 1 grain
     * - Player 'W' has resources available to swap 1 brick for 1 ore, using the knight
     * - Player 'W' has resources available to build a road at index 01 to 05 using 1 brick and 1 lumber
     * @param boardState: string representation of the board state.
     * @param actionSequence: array of strings, each representing one action
     * @return true if the sequence is executable, false otherwise.
     */
    public static boolean isActionSequenceValid(String boardState, String[] actionSequence) {
        // FIXME: Task 10a
        return false;
    }

    /**
     * Given a valid board state and a sequence of player actions, this
     * method returns the new board state after executing the sequence of
     * actions. You can assume that the sequence is executable.
     * For example : given boardState = "W63bbglowWR0205R0509S02XR3237W01X00", actionSequence = "buildK02","swapbo","buildR0105"
     * - The next boardState should be "X61[Next Resources]WK02R0105R0205R0509S02XR3237W01X00"
     * - Player 'W' knight at index 02 is built
     * - Player 'W' swaps a resource and the knight becomes used
     * - Player 'W' built road R0105
     * - Player 'W' turn ends and the current player becomes 'X'
     * - [Next Resources] can be any of 6 resources of player 'X'
     * @param boardState: string representation of the board state
     * @param actionSequence: array of strings, each representing one action
     * @return string representation of the new board state
     */
    public static String applyActionSequence(String boardState, String[] actionSequence) {
        // FIXME: Task 10b
        return null;
    }

    /**
     * Given a valid board state, return all applicable player action sequences.
     * The method should return an array of sequences, where each sequence is
     * an array of action string representations.
     *
     * If the current phase of the game is the Start of Game phase, each of
     * the sequences should contain just one road building action (that is
     * a permitted initial road for the player).
     *
     * If the current phase of the game is the Roll phase, each of the
     * sequences should contain just one action, specifying a possible
     * next roll (i.e., resources to keep).
     *
     * If the current phase is the Build phase, the sequences should be all
     * non-redundant sequences of trade, swap and build actions that the
     * player can take.
     *
     * In this context, an action sequence is considered non-redundant if
     * 1. All resources gained through trade and swap actions are totally used.
     *    i.e. the turn finishes with 0 of that resource.
     * 2. A trade action occurs at most once during the action sequence.
     * 3. Gained resources through the trade and swap actions are not later
     *    traded/swapped away.
     * 4. The empty sequence is always non-redundant (i.e. the player takes no
     *    action).
     *
     * Note, there are other sources of redundancy in action sequences besides the
     * ones that are listed here. One of the more noteworthy ones is the ordering of
     * actions within a sequence whereby two different action sequences may result
     * in the same state when applied. While this is not relevant for this task, it
     * may prove useful to consider this for your "smart" game AI in task 14.
     *
     * In the build phase, one of the possible sequences is always to end
     * the player's turn without taking any action, i.e., an empty sequence.
     *
     * The order of the action sequences in the return array is unspecified,
     * i.e., does not matter. (Of course, the order of actions within each
     * sequence does matter.)
     *
     * @param boardState: string representation of the current board state.
     * @return array of possible action sequences.
     */
    public static String[][] generateAllPossibleActionSequences(String boardState) {
        // FIXME: Task 12
        return null;
    }

    /**
     * Given a valid board state, return a valid action sequence.
     *
     * This method is the interface to your game AI. It is given the current
     * state of the game, and should return the sequence of actions your AI
     * chooses to take.
     *
     * An array of length 0 is interpreted as finishing the current turn
     * without taking any further action.
     *
     * @param boardState: string representation of the board state.
     * @return array of strings representing the actions the AI will take.
     */
    public static String[] generateAction(String boardState) {
        // FIXME: Task 13
        // FIXME: Task 14 Implement a "smart" generateAction()
        return null;
    }
}
