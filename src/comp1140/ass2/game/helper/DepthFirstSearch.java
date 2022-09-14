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
        if (currentPath.size() > 1) {
            List<Road> edges = new ArrayList<>();
            for (int i = 1; i < currentPath.size(); i++) {
                edges.add(new Road(currentPath.get(i), currentPath.get(i - 1)));
            }
            if (edges.contains(new Road(start, currentPath.get(currentPath.size() - 1)))) {
                return;
            }
        }

        currentPath.addLast(start);
        if (start == end) {
            paths.add(new ArrayList<>(currentPath));
            currentPath.removeLast();
            return;
        }
        for (int vertex : graph.get(start)) {
            search(vertex, end);
        }
        currentPath.removeLast();
    }

}
