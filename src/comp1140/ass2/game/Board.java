package comp1140.ass2.game;

import java.util.*;

public class Board {
    Map<Integer, Knight> knightBoard = new HashMap<>();
    Castle[] castleBoard = new Castle[4];
    GameGraph roadBoard;

    List<Integer> coastalIndex =  List.of(0,4,1,5,2,6,10,15,20,26,32,37,42,46,50,53,49,52,48,51,47,43,38,33,27,21,16,11,7,3);
    Map<Integer, Building> residentialBuilding = new HashMap<>();
    public Board () {
        initializeRoadBoard();
        initializeCastleBoard();
        initializeKnightBoard();
        initializeResidentialBuilding();
    }

    public GameGraph getRoadBoard() {
        return roadBoard;
    }

    public void initializeRoadBoard() {
        ArrayList<GameGraph.Edge> edges = new ArrayList<GameGraph.Edge>();
        edges.add(new GameGraph.Edge(0,3,null));
        edges.add(new GameGraph.Edge(0,4,null));
        edges.add(new GameGraph.Edge(1,4,null));
        edges.add(new GameGraph.Edge(1,5,null));
        edges.add(new GameGraph.Edge(2,5,null));
        edges.add(new GameGraph.Edge(2,6,null));
        edges.add(new GameGraph.Edge(3,0,null));
        edges.add(new GameGraph.Edge(3,7,null));
        edges.add(new GameGraph.Edge(4,0,null));
        edges.add(new GameGraph.Edge(4,1,null));
        edges.add(new GameGraph.Edge(4,8,null));
        edges.add(new GameGraph.Edge(5,1,null));
        edges.add(new GameGraph.Edge(5,2,null));
        edges.add(new GameGraph.Edge(5,9,null));
        edges.add(new GameGraph.Edge(6,2,null));
        edges.add(new GameGraph.Edge(6,10,null));
        edges.add(new GameGraph.Edge(7,3,null));
        edges.add(new GameGraph.Edge(7,11,null));
        edges.add(new GameGraph.Edge(7,12,null));
        edges.add(new GameGraph.Edge(8,4,null));
        edges.add(new GameGraph.Edge(8,12,null));
        edges.add(new GameGraph.Edge(8,13,null));
        edges.add(new GameGraph.Edge(9,13,null));
        edges.add(new GameGraph.Edge(9,5,null));
        edges.add(new GameGraph.Edge(9,14,null));
        edges.add(new GameGraph.Edge(10,6,null));
        edges.add(new GameGraph.Edge(10,14,null));
        edges.add(new GameGraph.Edge(10,15,null));
        edges.add(new GameGraph.Edge(11,7,null));
        edges.add(new GameGraph.Edge(11,16,null));
        edges.add(new GameGraph.Edge(12,7,null));
        edges.add(new GameGraph.Edge(12,8,null));
        edges.add(new GameGraph.Edge(12,17,null));
        edges.add(new GameGraph.Edge(13,9,null));
        edges.add(new GameGraph.Edge(13,8,null));
        edges.add(new GameGraph.Edge(13,18,null));
        edges.add(new GameGraph.Edge(14,9,null));
        edges.add(new GameGraph.Edge(14,10,null));
        edges.add(new GameGraph.Edge(14,19,null));
        edges.add(new GameGraph.Edge(15,10,null));
        edges.add(new GameGraph.Edge(15,20,null));
        edges.add(new GameGraph.Edge(16,11,null));
        edges.add(new GameGraph.Edge(16,21,null));
        edges.add(new GameGraph.Edge(16,22,null));
        edges.add(new GameGraph.Edge(17,12,null));
        edges.add(new GameGraph.Edge(17,22,null));
        edges.add(new GameGraph.Edge(17,23,null));
        edges.add(new GameGraph.Edge(18,13,null));
        edges.add(new GameGraph.Edge(18,23,null));
        edges.add(new GameGraph.Edge(18,24,null));
        edges.add(new GameGraph.Edge(19,14,null));
        edges.add(new GameGraph.Edge(19,24,null));
        edges.add(new GameGraph.Edge(19,25,null));
        edges.add(new GameGraph.Edge(20,15,null));
        edges.add(new GameGraph.Edge(20,25,null));
        edges.add(new GameGraph.Edge(20,26,null));
        edges.add(new GameGraph.Edge(21,16,null));
        edges.add(new GameGraph.Edge(21,27,null));
        edges.add(new GameGraph.Edge(22,16,null));
        edges.add(new GameGraph.Edge(22,17,null));
        edges.add(new GameGraph.Edge(22,28,null));
        edges.add(new GameGraph.Edge(23,17,null));
        edges.add(new GameGraph.Edge(23,18,null));
        edges.add(new GameGraph.Edge(23,29,null));
        edges.add(new GameGraph.Edge(24,18,null));
        edges.add(new GameGraph.Edge(24,19,null));
        edges.add(new GameGraph.Edge(24,30,null));
        edges.add(new GameGraph.Edge(25,19,null));
        edges.add(new GameGraph.Edge(25,20,null));
        edges.add(new GameGraph.Edge(25,31,null));
        edges.add(new GameGraph.Edge(26,20,null));
        edges.add(new GameGraph.Edge(26,32,null));
        edges.add(new GameGraph.Edge(27,21,null));
        edges.add(new GameGraph.Edge(27,33,null));
        edges.add(new GameGraph.Edge(28,22,null));
        edges.add(new GameGraph.Edge(28,33,null));
        edges.add(new GameGraph.Edge(28,34,null));
        edges.add(new GameGraph.Edge(29,23,null));
        edges.add(new GameGraph.Edge(29,34,null));
        edges.add(new GameGraph.Edge(29,35,null));
        edges.add(new GameGraph.Edge(30,24,null));
        edges.add(new GameGraph.Edge(30,35,null));
        edges.add(new GameGraph.Edge(30,36,null));
        edges.add(new GameGraph.Edge(31,36,null));
        edges.add(new GameGraph.Edge(31,25,null));
        edges.add(new GameGraph.Edge(31,37,null));
        edges.add(new GameGraph.Edge(32,26,null));
        edges.add(new GameGraph.Edge(32,37,null));
        edges.add(new GameGraph.Edge(33,27,null));
        edges.add(new GameGraph.Edge(33,28,null));
        edges.add(new GameGraph.Edge(33,38,null));
        edges.add(new GameGraph.Edge(34,28,null));
        edges.add(new GameGraph.Edge(34,29,null));
        edges.add(new GameGraph.Edge(34,39,null));
        edges.add(new GameGraph.Edge(35,29,null));
        edges.add(new GameGraph.Edge(35,30,null));
        edges.add(new GameGraph.Edge(35,40,null));
        edges.add(new GameGraph.Edge(36,30,null));
        edges.add(new GameGraph.Edge(36,31,null));
        edges.add(new GameGraph.Edge(36,41,null));
        edges.add(new GameGraph.Edge(37,31,null));
        edges.add(new GameGraph.Edge(37,32,null));
        edges.add(new GameGraph.Edge(37,42,null));
        edges.add(new GameGraph.Edge(38,43,null));
        edges.add(new GameGraph.Edge(38,33,null));
        edges.add(new GameGraph.Edge(39,34,null));
        edges.add(new GameGraph.Edge(39,43,null));
        edges.add(new GameGraph.Edge(39,44,null));
        edges.add(new GameGraph.Edge(40,35,null));
        edges.add(new GameGraph.Edge(40,44,null));
        edges.add(new GameGraph.Edge(40,45,null));
        edges.add(new GameGraph.Edge(41,45,null));
        edges.add(new GameGraph.Edge(41,36,null));
        edges.add(new GameGraph.Edge(41,46,null));
        edges.add(new GameGraph.Edge(42,37,null));
        edges.add(new GameGraph.Edge(42,46,null));
        edges.add(new GameGraph.Edge(43,38,null));
        edges.add(new GameGraph.Edge(43,39,null));
        edges.add(new GameGraph.Edge(43,47,null));
        edges.add(new GameGraph.Edge(44,39,null));
        edges.add(new GameGraph.Edge(44,40,null));
        edges.add(new GameGraph.Edge(44,48,null));
        edges.add(new GameGraph.Edge(45,40,null));
        edges.add(new GameGraph.Edge(45,41,null));
        edges.add(new GameGraph.Edge(45,49,null));
        edges.add(new GameGraph.Edge(46,41,null));
        edges.add(new GameGraph.Edge(46,42,null));
        edges.add(new GameGraph.Edge(46,50,null));
        edges.add(new GameGraph.Edge(47,43,null));
        edges.add(new GameGraph.Edge(47,51,null));
        edges.add(new GameGraph.Edge(48,44,null));
        edges.add(new GameGraph.Edge(48,51,null));
        edges.add(new GameGraph.Edge(48,52,null));
        edges.add(new GameGraph.Edge(49,45,null));
        edges.add(new GameGraph.Edge(49,52,null));
        edges.add(new GameGraph.Edge(49,53,null));
        edges.add(new GameGraph.Edge(50,46,null));
        edges.add(new GameGraph.Edge(50,53,null));
        edges.add(new GameGraph.Edge(51,47,null));
        edges.add(new GameGraph.Edge(51,48,null));
        edges.add(new GameGraph.Edge(52,48,null));
        edges.add(new GameGraph.Edge(52,49,null));
        edges.add(new GameGraph.Edge(53,49,null));
        edges.add(new GameGraph.Edge(53,50,null));
        roadBoard = new GameGraph(edges);
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
        if (house instanceof Settlement) {
            house = (Settlement) house;
            if (((Settlement) house).isUpgradeable && house.owner == player) {
                residentialBuilding.replace(houseNumber, new City(player));
                return 1;
            }
        }
        return 0;
    }
    public int upgradeToSettlement(int houseNumber, Player player) {
        Building house = residentialBuilding.get(houseNumber);
        if (house instanceof Settlement) {
            house = (Settlement) house;
            if (house.owner == null) {
                System.out.println("yes");
                house.setOwner(player);
                return 1;
            }
        }
        return 0;
    }
    public boolean isRoadValid(int startPos, int endPos) {
        ArrayList<GameGraph.Node> nodeArrayList = roadBoard.adjacencyMatrix.get(startPos);
        for (var node : nodeArrayList) {
            if (node.location == endPos) {
                return true;
            }
        }
        return false;
    }

    public void updateBoardUsingEncodedString (String structureIdentifier, Player player) {
        char buildingType = structureIdentifier.charAt(0);
        if (buildingType == 'R') {
            int source = Integer.parseInt(structureIdentifier.substring(1,3));
            int destination = Integer.parseInt(structureIdentifier.substring(3,5));
            for (GameGraph.Node node : roadBoard.adjacencyMatrix.get(source)) {
                if (node.location == destination) {
                    node.player = player;
                }
            }
            for (GameGraph.Node node : roadBoard.adjacencyMatrix.get(destination)) {
                if (node.location == source) {
                    node.player = player;
                }
            }
        }else if (buildingType == 'C') {
            int location = Integer.parseInt(structureIdentifier.substring(1,2));
            Castle castle = castleBoard[location];
            castle.setOwner(player);
        }  else {
            int location = Integer.parseInt(structureIdentifier.substring(1,3));
            if (buildingType == 'J' || buildingType == 'K') {
                Knight currentKnight = knightBoard.get(location);
                if (buildingType == 'K')
                    currentKnight.setJoker(true);
                currentKnight.setOwner(player);
            } else if (buildingType == 'S') {
                Building settlement = residentialBuilding.get(location);
                settlement.setOwner(player);
            } else if (buildingType == 'T') {
                Building city = residentialBuilding.get(location);
                city.setOwner(player);  // TODO refactor later
            }
        }
    }

    /**
     * determine if road is buildable with the conditions of being connected by another road and cannot build further if
     * there is a settlement or a city in between
     * @param firstPos start position of the road
     * @param secondPos end position of the road
     * @param player the current player that want to check
     * @return boolean value
     */
    public boolean isRoadBuildable(int firstPos, int secondPos, Player player) {
        for (int n : new int[] {firstPos, secondPos}) {
            for(GameGraph.Node node : roadBoard.adjacencyMatrix.get(n)) {
                Building house = residentialBuilding.get(n);
                if (node.location != secondPos && node.player != null &&  node.player.equals(player)) {
                    if (house != null && house.getOwner() != null && house.getOwner().equals(player)) {
                        return true;
                    } else if (house == null) return true;
                }
            }
        }

        return false;
    }


    public boolean canKnightBuild(int position, Player player) {
        int[] neighboursIndexList = knightBoard.get(position).getNeighbours();
        for (int n1 : neighboursIndexList) {
            for (GameGraph.Node node : roadBoard.adjacencyMatrix.get(n1)) {
                if (Arrays.stream(neighboursIndexList).anyMatch(e -> e == node.location)
                        && node.player != null && node.player.equals(player)) {
                    return true;
                }
                }
        }
        return false;
    }

    public boolean canCastleBuild(int location) {
        Castle castle = castleBoard[location];
        if (castle.getOwner() == null) {
            return true;
        }
        return false;
    }

    public boolean canSettlementBuild(int location, Player player) {
        if (residentialBuilding.get(location).getOwner() == null) {
            for (GameGraph.Node node : roadBoard.adjacencyMatrix.get(location)) {
                if (node.player != null && node.player.equals(player))
                    return true;
            }
        }
        return false;
    }

    public boolean canCityBuild(int location, Player player) {
        Player owner = residentialBuilding.get(location).getOwner();
        if (owner.equals(player)) {
            Settlement settlement = (Settlement) residentialBuilding.get(location);
            if (settlement.isUpgradeable)
                return true;
        }
        return false;
    }

    public boolean isKnightResourceAvailable(Resource neededResource, Player player) {
        for (Knight knight : knightBoard.values()) {
            if (!knight.isJoker() && knight.getOwner() != null &&
                    knight.getOwner().equals(player) && knight.getJokerResource() == neededResource) {
                return true;
            }
        }
        Knight wildCardKnight1 = knightBoard.get(9);
        Knight wildCardKnight2 = knightBoard.get(10);
        if (wildCardKnight1.getOwner() != null && wildCardKnight2.getOwner() != null &&
                wildCardKnight1.getOwner().equals(player) && !wildCardKnight1.isJoker() || !wildCardKnight2.isJoker()) {
            return true;
        }
        return false;
    }

    public Boolean isCoastalAnd5RoadsAway(int firstPos, int secondPos, Player player) {
        int firstPosIndex = coastalIndex.indexOf(firstPos);
        int secondPosIndex = coastalIndex.indexOf(secondPos);
        int coastalSize = coastalIndex.size();
        if (coastalIndex.contains(firstPos) && coastalIndex.contains(secondPos)) {
            for (int n : coastalIndex) {
                if (n == firstPos || n == secondPos) continue;
                for (var node : roadBoard.adjacencyMatrix.get(n)) {
                    if (node.player != null && coastalIndex.contains(node.location)) {
                        int index1 = coastalIndex.indexOf(n);
                        int index2 = coastalIndex.indexOf(node.location);
                        int forwardDistance = Math.min(Math.min(Math.abs(firstPosIndex - index1), Math.abs(firstPosIndex - index2)),
                                Math.min(Math.abs(secondPosIndex - index1),Math.abs(secondPosIndex - index2)));
                        int backwardDistance = Math.min(Math.min(Math.abs(firstPosIndex - coastalSize - index1),
                                Math.abs(firstPosIndex - coastalSize - index2)), Math.min(Math.abs(secondPosIndex - coastalSize - index1),
                                Math.abs(secondPosIndex - coastalSize - index2)));
                        System.out.println(forwardDistance);
                        System.out.println(backwardDistance);
                        if (forwardDistance < 5 || backwardDistance < 5) return false;
                    }
                }
            }
        } else return false;
        return true;
    }

    public int[] countRoads(Player player1, Player player2) {
        int player1Count = 0;
        int player2Count = 0;
        for (int i = 0; i < 54; i++) {
            for (var node : roadBoard.adjacencyMatrix.get(i)) {
                if (i < node.location && node.player != null) {
                    if (node.player.equals(player1)) player1Count++;
                    if (node.player.equals(player2)) player2Count++;
                }
            }
        }
        return new int[]{player1Count,player2Count};
    }

    public Map<Integer, Knight> getKnightBoard() {
        return knightBoard;
    }


}
