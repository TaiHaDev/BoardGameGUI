package comp1140.ass2;

import comp1140.ass2.game.DepthFirstSearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GraphTheoryAlgorithmsTests {

    @Test
    public void dfs_returns_the_possible_paths_from_start_to_finish() {
        Set<List<Integer>> paths = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(1, 2, 3, 4));
        graph.put(1, List.of(2, 0));
        graph.put(2, List.of(3, 0));
        graph.put(3, List.of(2, 0));
        graph.put(4, List.of(0));
        DepthFirstSearch dfs = new DepthFirstSearch(paths, graph);
        dfs.search(0, 3);

        Set<List<Integer>> expected = Set.of(
                List.of(0, 1, 2, 0, 3),
                List.of(0, 1, 2, 3),
                List.of(0, 2, 3),
                List.of(0, 3)
        );
        Assertions.assertEquals(expected, paths);
    }

    @Test
    public void the_longest_path_of_an_eulerian_trail_has_the_length_of_the_trail_itself() {
        Set<List<Integer>> paths = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(4, 1));
        graph.put(1, List.of(0, 2));
        graph.put(2, List.of(1, 3));
        graph.put(3, List.of(2, 4));
        graph.put(4, List.of(3, 0));
        DepthFirstSearch dfs = new DepthFirstSearch(paths, graph);
        dfs.search(0, 4);

        paths.stream()
                .max(Comparator.comparingInt(List::size))
                .ifPresentOrElse(path -> Assertions.assertEquals(5, path.size()), Assertions::fail);
    }

    @Test
    public void search_does_not_get_stuck_on_cycles() {
        Set<List<Integer>> paths = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(4, 1));
        graph.put(1, List.of(0, 2));
        graph.put(2, List.of(1, 3));
        graph.put(3, List.of(2, 4));
        graph.put(4, List.of(3, 0));

        DepthFirstSearch dfs = new DepthFirstSearch(paths, graph);
        dfs.search(0, 4);

        Assertions.assertEquals(2, paths.size());
        Assertions.assertEquals(Set.of(List.of(0, 1, 2, 3, 4), List.of(0, 4)), paths);
    }

    @Test
    public void a_straight_line_is_classified_as_an_eulerian_trail() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(1));
        graph.put(1, List.of(0, 2));
        graph.put(2, List.of(3));
        Assertions.assertTrue(CatanDiceExtra.isEulerianTrail(graph));
    }

    @Test
    public void a_cycle_is_classified_as_an_eulerian_trail() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(4, 1));
        graph.put(1, List.of(0, 2));
        graph.put(2, List.of(1, 3));
        graph.put(3, List.of(2, 4));
        graph.put(4, List.of(3, 0));
        Assertions.assertTrue(CatanDiceExtra.isEulerianTrail(graph));
    }

    @Test
    public void a_disconnected_graph_is_not_classified_as_an_eulerian_trail() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(1));
        graph.put(1, List.of(0));
        graph.put(2, List.of(3));
        graph.put(3, List.of(2));
        Assertions.assertFalse(CatanDiceExtra.isEulerianTrail(graph));
    }

    @Test
    public void a_straight_line_path_can_be_converted_to_a_graph() {
        Map<Integer, List<Integer>> graph = CatanDiceExtra.pathToGraph(List.of(0, 1, 2, 3, 4));

        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(0, List.of(1));
        expected.put(1, List.of(0, 2));
        expected.put(2, List.of(1, 3));
        expected.put(3, List.of(2, 4));
        expected.put(4, List.of(3));

        Assertions.assertEquals(expected, graph);
    }

    @Test
    public void a_cycle_can_be_converted_to_a_graph() {
        Map<Integer, List<Integer>> graph = CatanDiceExtra.pathToGraph(List.of(0, 1, 2, 0));

        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(0, List.of(1, 2));
        expected.put(1, List.of(0, 2));
        expected.put(2, List.of(1, 0));

        Assertions.assertEquals(expected, graph);
    }

}
