package comp1140.ass2.board;

import comp1140.ass2.game.Matrices;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.buildings.*;
import comp1140.ass2.helpers.DepthFirstSearch;

import java.util.*;

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

    public void initializeCastleBoard() {
        castleBoard[0] = new Castle(null);
        castleBoard[1] = new Castle(null);
        castleBoard[2] = new Castle(null);
        castleBoard[3] = new Castle(null);
    }

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

    public int upgradeToCity(int houseNumber, Player player) {
        Building house = residentialBuilding.getOrDefault(houseNumber,null);
        if (house instanceof Settlement settlement) {
            if (settlement.isUpgradeable() && house.getOwner() == player) {
                residentialBuilding.replace(houseNumber, new City(player));
                return 1;
            }
        }
        return 0;
    }

    public int upgradeToSettlement(int houseNumber, Player player) {
        Building house = residentialBuilding.get(houseNumber);
        if (house instanceof Settlement) {
            if (house.getOwner() == null) {
                System.out.println("yes");
                house.setOwner(player);
                return 1;
            }
        }
        return 0;
    }

    public boolean isRoadValid(int startPos, int endPos) {
        return roadBoard.getAdjacencyMatrix()[startPos][endPos] == 1;
    }

//    public void updateBoardUsingEncodedString (String structureIdentifier, Player player) {
//        char buildingType = structureIdentifier.charAt(0);
//        if (buildingType == 'R') {
//            int source = Integer.parseInt(structureIdentifier.substring(1,3));
//            int destination = Integer.parseInt(structureIdentifier.substring(3,5));
//            for (GameGraph.Node node : roadBoard.getAdjacencyMatrix().get(source)) {
//                if (node.id == destination) {
//                    node.player = player;
//                }
//            }
//            for (GameGraph.Node node : roadBoard.adjacencyMatrix.get(destination)) {
//                if (node.id == source) {
//                    node.player = player;
//                }
//            }
//        }else if (buildingType == 'C') {
//            int location = Integer.parseInt(structureIdentifier.substring(1,2));
//            Castle castle = castleBoard[location];
//            castle.setOwner(player);
//        }  else {
//            int location = Integer.parseInt(structureIdentifier.substring(1,3));
//            if (buildingType == 'J' || buildingType == 'K') {
//                Knight currentKnight = knightBoard.get(location);
//                if (buildingType == 'K')
//                    currentKnight.setJoker(true);
//                currentKnight.setOwner(player);
//            } else if (buildingType == 'S') {
//                Building settlement = residentialBuilding.get(location);
//                settlement.setOwner(player);
//            } else if (buildingType == 'T') {
//                Building city = residentialBuilding.get(location);
//                city.setOwner(player);  // TODO refactor later
//            }
//        }
//    }

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
        if (!Arrays.asList(roads).contains(new Road(firstPos, secondPos))) return false;
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

    public boolean canKnightBuild(int position, Player player) {
        boolean canKnightBuild = false;
        for (int neighbourIndex : knightBoard.get(position).getNeighbours()) {
            if (Arrays.stream(roads).anyMatch(road -> road.getStart() == neighbourIndex || road.getEnd() == neighbourIndex)
                    || player.equals(residentialBuilding.get(neighbourIndex).getOwner())) {
                canKnightBuild = true;
            }
        }
        return canKnightBuild;
    }

    public boolean canCastleBuild(int location) {
        Castle castle = castleBoard[location];
        return castle.getOwner() == null;
    }

    public boolean canSettlementBuild(int location, Player player) {
        return Arrays.stream(roadBoard.getAdjacencyMatrix()[location])
                .filter(e -> roadBoard.getAdjacencyMatrix()[location][e] == 1)
                .allMatch(e -> residentialBuilding.get(e) == null ||
                        residentialBuilding.get(e).getOwner() == null ||
                        player.equals(residentialBuilding.get(e).getOwner()));
    }

    public boolean canCityBuild(int location, Player player) {
        Player owner = residentialBuilding.get(location).getOwner();
        Settlement settlement = (Settlement) residentialBuilding.get(location);
        return owner == player && settlement.isUpgradeable();
    }

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

    public static Player hasLongestRoad(GameInstance game) {
        HashMap<Player, Integer> playersAndTheirLongestRoad = new HashMap<>();
        for (Player player : game.getPlayers()) {
            // initialise a new graph that only contains the current player's
            // owned roads.
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Road> ownedRoads = new HashSet<>();
            for (Road road : game.getBoard().getRoads()) {
                if (player.equals(road.getOwner())) {
                    ownedRoads.add(road);
                }
            }
            for (Road road : ownedRoads) {
                List<Integer> values = graph.getOrDefault(road.getStart(), new ArrayList<>());
                values.add(road.getEnd());
                graph.put(road.getStart(), values);

                values = graph.getOrDefault(road.getEnd(), new ArrayList<>());
                values.add(road.getStart());
                graph.put(road.getEnd(), values);
            }

            // create a list of every path existing in the player's graph
            HashSet<List<Integer>> paths = new HashSet<>();
            for (int start : graph.keySet()) {
                if (graph.get(start).size() == 2) continue;
                for (int end : graph.keySet()) {
                    if (graph.get(end).size() == 2 || start == end) continue;
                    DepthFirstSearch dfs = new DepthFirstSearch(paths, graph);
                    dfs.search(start, end);
                }
            }

            int max = 0;

            for (List<Integer> path : paths) {
                if (isEulerianTrail(pathToGraph(path))) {
                    max = Math.max(path.size() - 1, max);
                }
            }
            playersAndTheirLongestRoad.put(player, max);
        }
        Player p = null;
        int max = 0;
        for (var entry : playersAndTheirLongestRoad.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                p = entry.getKey();
            } else if (entry.getValue() == max) return null;
        }
        if(playersAndTheirLongestRoad.get(p) > 4) return p;
        return null;
    }

    public static Player hasLargestArmy(GameInstance game) {
        HashMap<Player, Integer> playersAndTheirKnightCount = new HashMap();
        game.getPlayers()
                .stream()
                .forEach(
                        player -> playersAndTheirKnightCount.put(player,(int) game
                                .getBoard()
                                .getKnightBoard()
                                .values()
                                .stream()
                                .filter(knight -> player.equals(knight.getOwner())).count()));
        if (playersAndTheirKnightCount.isEmpty()) return null;
        Player p = null;
        int max = 0;
        for (var entry : playersAndTheirKnightCount.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                p = entry.getKey();
            } else if (entry.getValue() == max) return null;
        }
        if(playersAndTheirKnightCount.get(p) > 2) return p;
        return null;
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