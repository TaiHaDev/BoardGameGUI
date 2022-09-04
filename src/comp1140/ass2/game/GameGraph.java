package comp1140.ass2.game;

import java.util.ArrayList;

public class GameGraph {
    ArrayList<ArrayList<Node>> adjacencyMatrix = new ArrayList<>();

    public GameGraph(ArrayList<Edge> edges) {
        for (int i = 0; i < 54; i++) {
            adjacencyMatrix.add(i, new ArrayList<Node>());
        }
        for (var edge : edges) {
            adjacencyMatrix.get(edge.source).add(new Node(edge.destination, edge.player));
        }
    }

    public void showingGraph() {
        int count = 0;
        int end = adjacencyMatrix.size();
        while (count < end) {
            System.out.println(
                    "place Number: " + count + " And there" +
                            "are " + adjacencyMatrix.get(count).size()
                    + "direction(s) to go."
            );

            for (Node node : adjacencyMatrix.get(count)) {
                System.out.println(node.location + " is owned by " + node.player);

            }
            count++;
        }
    }
    public static class Edge {
        int source;
        int destination;
        Player player;
        public Edge(int source, int destination,Player player) {
            this.source = source;
            this.destination = destination;
            this.player = player;
        }
    }
    public static class Node {
        int location;
        Player player;
        public Node(int location, Player player) {
            this.location = location;
            this.player = player;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "location=" + location +
                    ", player=" + player +
                    '}';
        }
    }

}
