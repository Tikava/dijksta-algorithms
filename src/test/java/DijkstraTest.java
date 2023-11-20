import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class DijkstraTest {

    @ParameterizedTest
    @ValueSource(ints = { 10, 100, 1000, 10000, 100000 })
    public void testOriginalDijkstra(int graphSize) {
        List<Node<Integer>> graph = GraphGenerator.generateRandomGraph(graphSize);

        Dijkstra<Integer> dijkstra = new Dijkstra<>();
        long startTime = System.currentTimeMillis();
        dijkstra.calculateShortestPath(graph.get(0));
        long endTime = System.currentTimeMillis();

        System.out.println("Original Dijkstra Time for " + graphSize + " nodes: " + (endTime - startTime) + " ms");
    }

    @ParameterizedTest
    @ValueSource(ints = { 10, 100, 1000, 10000, 100000, 1000000 })
    public void testImprovedDijkstra(int graphSize) {
        List<Node<Integer>> graph = GraphGenerator.generateRandomGraph(graphSize);

        ImprovedDijkstra<Integer> improvedDijkstra = new ImprovedDijkstra<>();
        long startTime = System.currentTimeMillis();
        improvedDijkstra.calculateShortestPath(graph.get(0));
        long endTime = System.currentTimeMillis();
        System.out.println("Improved Dijkstra Time for " + graphSize + " nodes: " + (endTime - startTime) + " ms");
    }
}
