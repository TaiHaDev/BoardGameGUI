package comp1140.ass2.board;

import comp1140.ass2.game.Matrices;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.buildings.*;
import comp1140.ass2.helpers.DepthFirstSearch;

import java.util.*;
import java.util.stream.Stream;

import static comp1140.ass2.CatanDiceExtra.isEulerianTrail;
import static comp1140.ass2.CatanDiceExtra.pathToGraph;

public class Board {

    private Map<Integer, Knight> knightBoard = new HashMap<>();
    private final Castle[] castleBoard = new Castle[4];
    private final GameGraph roadBoard = new GameGraph();
    private final Road[] roads = new Road[GameGraph.EDGES];

    private final List<Integer> coastalIndex = List.of(0,4,1,5,2,6,10,15,20,26,32,37,42,46,50,53,49,52,48,51,47,43,38,33,27,21,16,11,7,3);
    private final Map<Integer, Building> residentialBuilding = new HashMap<>();

    public Board () {
        initializeCastleBoard();
        initializeKnightBoard();
        initializeResidentialBuilding();

        int i = 0;
        for (Map.Entry<Integer, List<Integer>> entry : roadBoard.getGraphMap().entrySet()) {
            int from = entry.getKey();
            for (int to : entry.getValue()) {
                Road road = new Road(from, to);
                if (Arrays.stream(roads).noneMatch(road::equals)) {
                    roads[i++] = road;
                }
            }
        }
    }
    public GameGraph getRoadBoard() {
        return roadBoard;
    }
    // Author Phuoc
    public void initializeCastleBoard() {
        castleBoard[0] = new Castle(null);
        castleBoard[1] = new Castle(null);
        castleBoard[2] = new Castle(null);
        castleBoard[3] = new Castle(null);
    }
    // author Phuoc
    public void initializeKnightBoard() {
        knightBoard.put(0,new Knight(null, Resource.WOOL,false, false, new int[] {0,3,4,7,8,12}));
        knightBoard.put(1,new Knight(null, Resource.GRAIN,false, false, new int[] {1,4,5,8,9,13}));
        knightBoard.put(2,new Knight(null, Resource.ORE,false, false, new int[] {2,5,6,9,10,14}));
        knightBoard.put(3,new Knight(null, Resource.ORE,false, false, new int[] {7,11,12,16,17,22}));
        knightBoard.put(4,new Knight(null, Resource.BRICK,false, false, new int[] {8,12,13,17,18,23}));
        knightBoard.put(5,new Knight(null, Resource.LUMBER,false, false, new int[] {9,13,14,18,19,24}));
        knightBoard.put(6,new Knight(null, Resource.WOOL,false, false, new int[] {10,14,15,19,20,25}));
        knightBoard.put(7,new Knight(null, Resource.GRAIN,false, false, new int[] {16,21,22,27,28,33}));
        knightBoard.put(8,new Knight(null, Resource.LUMBER,false, false, new int[] {17,22,23,28,29,34}));
        knightBoard.put(9,new Knight(null, null,false, true, new int[] {18,23,24,39,30,35}));
        knightBoard.put(10,new Knight(null, null,false, true, new int[] {18,23,24,39,30,35}));
        knightBoard.put(11,new Knight(null, Resource.BRICK,false, false, new int[] {19,24,25,30,31,36}));
        knightBoard.put(12,new Knight(null, Resource.GRAIN,false, false, new int[] {20,25,26,31,32,37}));
        knightBoard.put(13,new Knight(null, Resource.WOOL,false, false, new int[] {28,33,34,38,39,43}));
        knightBoard.put(14,new Knight(null, Resource.BRICK,false, false, new int[] {29,34,35,39,40,44}));
        knightBoard.put(15,new Knight(null, Resource.LUMBER,false, false, new int[] {30,35,36,40,41,45}));
        knightBoard.put(16,new Knight(null, Resource.ORE,false, false, new int[] {31,36,37,41,42,46}));
        knightBoard.put(17,new Knight(null, Resource.ORE,false, false, new int[] {39,43,44,47,51,48}));
        knightBoard.put(18,new Knight(null, Resource.GRAIN,false, false, new int[] {40,44,45,48,49,52}));
        knightBoard.put(19,new Knight(null, Resource.WOOL,false, false, new int[] {41,45,46,49,40,53}));
    }
    // author Phuoc
    public void initializeResidentialBuilding() {
        residentialBuilding.put(0,new Settlement(null, false));
        residentialBuilding.put(1,new Settlement(null, true));
        residentialBuilding.put(2,new Settlement(null, false));
        residentialBuilding.put(7,new Settlement(null, true));
        residentialBuilding.put(8,new Settlement(null, false));
        residentialBuilding.put(9,new Settlement(null, false));
        residentialBuilding.put(10,new Settlement(null, true));
        residentialBuilding.put(16,new Settlement(null, false));
        residentialBuilding.put(17,new Settlement(null, true));
        residentialBuilding.put(18,new Settlement(null, true));
        residentialBuilding.put(19,new Settlement(null, true));
        residentialBuilding.put(20,new Settlement(null, false));
        residentialBuilding.put(33,new Settlement(null, false));
        residentialBuilding.put(34,new Settlement(null, true));
        residentialBuilding.put(35,new Settlement(null, true));
        residentialBuilding.put(36,new Settlement(null, true));
        residentialBuilding.put(37,new Settlement(null, false));
        residentialBuilding.put(43,new Settlement(null, true));
        residentialBuilding.put(44,new Settlement(null, false));
        residentialBuilding.put(45,new Settlement(null, false));
        residentialBuilding.put(46,new Settlement(null, true));
        residentialBuilding.put(51,new Settlement(null, false));
        residentialBuilding.put(52,new Settlement(null, true));
        residentialBuilding.put(53,new Settlement(null, false));
    }
    public boolean isRoadValid(int startPos, int endPos) {
        return roadBoard.getAdjacencyMatrix()[startPos][endPos] == 1;
    }
    // author Phuoc
    /**
     * determine if road is buildable with the conditions of
     * being connected by another road and cannot build further if
     * there is a settlement or a city in between
     * @param firstPos start position of the road
     * @param secondPos end position of the road
     * @param player the current player that want to check
     * @return boolean value
     */
    public boolean isRoadBuildable(int firstPos, int secondPos, Player player) {
        if (!Arrays.asList(roads).contains(new Road(firstPos, secondPos, null))) return false;
        for (int n : new int[] { firstPos, secondPos }) {
            Building house = residentialBuilding.get(n);
            for (int node : getRoadBoard().getGraphMap().get(n)) {
                Road road = new Road(n, node, player);
                if (Arrays.asList(roads).contains(road) && player.equals(road.getOwner())) {
                    if (house == null || player.equals(house.getOwner())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // author Phuoc
    public boolean canKnightBuild(int position, Player player) {
        boolean canKnightBuild = false;
        if (knightBoard.get(position).getOwner() != null) return false;
        int[] neighbourIndex = knightBoard.get(position).getNeighbours();
        for (int i : neighbourIndex) {
            for (int j : neighbourIndex) {
                if (i == j) continue;
                if (Arrays.asList(roads).contains(new Road(i, j, player))) {
                    return true;
                }
            }
        }
        return canKnightBuild;
    }
    // author Phuoc
    public boolean canCastleBuild(int location) {
        Castle castle = castleBoard[location];
        return castle.getOwner() == null;
    }
    // author Phuoc
    public boolean canSettlementBuild(int location, Player player) {
        if (!residentialBuilding.containsKey(location) || residentialBuilding.get(location).getOwner() != null) {
            return false;
        }
        for (int i = 0; i < roadBoard.getAdjacencyMatrix()[location].length; i++) {
            if (roadBoard.getAdjacencyMatrix()[location][i] == 1 && List.of(roads).contains(new Road(location, i, player))) {
                return true;
            }
        }
        return false;
    }
    // author Phuoc
    public boolean canCityBuild(int location, Player player) {
        if (!residentialBuilding.containsKey(location)) return false;
        Player owner = residentialBuilding.get(location).getOwner();
        if (!(residentialBuilding.get(location) instanceof Settlement settlement)) {
            return false;
        }
        return owner == player && settlement.isUpgradeable();
    }
    // author Phuoc
    public boolean isKnightResourceAvailable(Resource neededResource, Player player) {
        for (Knight knight : knightBoard.values()) {
            if (!knight.isJoker() && player.equals(knight.getOwner()) && knight.getJokerResource() == neededResource) {
                return true;
            }
        }
        Knight wildCardKnight1 = knightBoard.get(9);
        Knight wildCardKnight2 = knightBoard.get(10);
        return (player.equals(wildCardKnight1.getOwner()) && !wildCardKnight1.isJoker()) ||
                        (player.equals(wildCardKnight2.getOwner()) && !wildCardKnight2.isJoker());
    }
    // author Matthew
    /**
     * Determines whether a hypothetical road starting
     * at {@param firstPos} and ending at {@param secondPos} would
     * lie on the edge of the map (i.e., coastal), and is also at
     * least five roads away from all other player's (any player who
     * is not {@param player}) roads.
     *
     * @param firstPos  the starting position of the road
     * @param secondPos the end position of the road
     * @param player    the player who would be building this road
     * @return true iff the road is coastal and 5 roads away from all other players' roads.
     */
    public boolean isCoastalAnd5RoadsAway(int firstPos, int secondPos, Player player) {
        boolean isRoadCoastal = coastalIndex.contains(firstPos) && coastalIndex.contains(secondPos);
        boolean isRoadStartAndEndAdjacent = roadBoard.getAdjacencyMatrix()[firstPos][secondPos] == 1;
        boolean isRoad5RoadsAwayFromPrevious = true;
        int[][] adjacencyMatrixWithFiveSteps = Matrices.pow(getRoadBoard().getAdjacencyMatrix(), 4);
        for (Road road : roads) {
            if (!(player.equals(road.getOwner()) || road.getOwner() == null) &&
                    (adjacencyMatrixWithFiveSteps[firstPos][road.getStart()] > 0 ||
                    adjacencyMatrixWithFiveSteps[secondPos][road.getStart()] > 0 ||
                    adjacencyMatrixWithFiveSteps[firstPos][road.getEnd()] > 0 ||
                    adjacencyMatrixWithFiveSteps[secondPos][road.getEnd()] > 0)) {
                isRoad5RoadsAwayFromPrevious = false;
                break;
            }
        }

        return isRoadCoastal && isRoadStartAndEndAdjacent && isRoad5RoadsAwayFromPrevious;
    }

    public Map<Integer, Knight> getKnightBoard() {
        return knightBoard;
    }

    public Castle[] getCastleBoard() {
        return castleBoard;
    }

    public void setKnightBoard(Map<Integer, Knight> knightBoard) {
        this.knightBoard = knightBoard;
    }

    public Road[] getRoads() {
        return roads;
    }

    public List<Integer> getCoastalIndex() {
        return coastalIndex;
    }

    public Map<Integer, Building> getResidentialBuilding() {
        return residentialBuilding;
    }

}
