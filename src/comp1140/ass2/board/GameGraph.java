package comp1140.ass2.board;

import java.util.*;

public class GameGraph {

    public static final int VERTICES = 54;
    public static final int EDGES = 72;

    private final int[][] adjacencyMatrix = new int[VERTICES][VERTICES];

    private final Map<Integer, List<Integer>> graphMap;

    public GameGraph() {
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        graphMap.put(0, List.of(3, 4));
        graphMap.put(1, List.of(4, 5));
        graphMap.put(2, List.of(5, 6));
        graphMap.put(3, List.of(0, 7));
        graphMap.put(4, List.of(0, 1, 8));
        graphMap.put(5, List.of(1, 2, 9));
        graphMap.put(6, List.of(2, 10));
        graphMap.put(7, List.of(3, 11, 12));
        graphMap.put(8, List.of(4, 12, 13));
        graphMap.put(9, List.of(5, 13, 14));
        graphMap.put(10, List.of(6, 14, 15));
        graphMap.put(11, List.of(7, 16));
        graphMap.put(12, List.of(7, 8, 17));
        graphMap.put(13, List.of(8, 9, 18));
        graphMap.put(14, List.of(9, 10, 19));
        graphMap.put(15, List.of(10, 20));
        graphMap.put(16, List.of(11, 21, 22));
        graphMap.put(17, List.of(12, 22, 23));
        graphMap.put(18, List.of(13, 23, 24));
        graphMap.put(19, List.of(14, 24, 25));
        graphMap.put(20, List.of(15, 25, 26));
        graphMap.put(21, List.of(16, 27));
        graphMap.put(22, List.of(16, 17, 28));
        graphMap.put(23, List.of(17, 18, 29));
        graphMap.put(24, List.of(18, 19, 30));
        graphMap.put(25, List.of(19, 20, 31));
        graphMap.put(26, List.of(20, 32));
        graphMap.put(27, List.of(21, 33));
        graphMap.put(28, List.of(22, 33, 34));
        graphMap.put(29, List.of(23, 34, 35));
        graphMap.put(30, List.of(24, 35, 36));
        graphMap.put(31, List.of(25, 36, 37));
        graphMap.put(32, List.of(26, 37));
        graphMap.put(33, List.of(27, 28, 38));
        graphMap.put(34, List.of(28, 29, 39));
        graphMap.put(35, List.of(29, 30, 40));
        graphMap.put(36, List.of(30, 31, 41));
        graphMap.put(37, List.of(31, 32, 42));
        graphMap.put(38, List.of(33, 43));
        graphMap.put(39, List.of(34, 43, 44));
        graphMap.put(40, List.of(35, 44, 45));
        graphMap.put(41, List.of(36, 45, 46));
        graphMap.put(42, List.of(37, 46));
        graphMap.put(43, List.of(38, 39, 47));
        graphMap.put(44, List.of(39, 40, 48));
        graphMap.put(45, List.of(40, 41, 49));
        graphMap.put(46, List.of(41, 42, 50));
        graphMap.put(47, List.of(43, 51));
        graphMap.put(48, List.of(44, 51, 52));
        graphMap.put(49, List.of(45, 52, 53));
        graphMap.put(50, List.of(46, 53));
        graphMap.put(51, List.of(47, 48));
        graphMap.put(52, List.of(48, 49));
        graphMap.put(53, List.of(49, 50));
        this.graphMap = graphMap;

        for (Map.Entry<Integer, List<Integer>> entry : graphMap.entrySet()) {
            for (int to : entry.getValue()) {
                int from = entry.getKey();
                this.adjacencyMatrix[from][to] = 1;
            }
        }

    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Map<Integer, List<Integer>> getGraphMap() {
        return graphMap;
    }

}
