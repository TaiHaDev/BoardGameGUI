package comp1140.ass2.game.helper;

import comp1140.ass2.game.buildings.Road;

import java.util.*;

public class DepthFirstSearch {

    private final HashSet<List<Integer>> paths;
    private final LinkedList<Integer> currentPath = new LinkedList<>();
    private final Map<Integer, List<Integer>> graph;

    public DepthFirstSearch(HashSet<List<Integer>> paths, Map<Integer, List<Integer>> graph) {
        this.paths = paths;
        this.graph = graph;
    }

    public void search(int start, int end) {
        // if our new edge has already been used, abandon this path
        if (currentPath.size() > 2) {
            List<Road> edgesVisited = new ArrayList<>();
            for (int i = 1; i < currentPath.size(); i++) {
                Road road = new Road(currentPath.get(i - 1), currentPath.get(i));
                if (edgesVisited.contains(road)) {
                    return;
                }
                edgesVisited.add(road);
            }
        }

        currentPath.addLast(start);
        if (start == end) {
            List<Integer> copy = new ArrayList<>(currentPath);
            paths.add(new ArrayList<>(copy));
            currentPath.removeLast();
            return;
        }
        for (int vertex : graph.get(start)) {
            search(vertex, end);
        }
        currentPath.removeLast();
    }

}
