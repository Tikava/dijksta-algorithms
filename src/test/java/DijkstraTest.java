import org.junit.jupiter.api.Test;

import java.util.List;


public class DijkstraTest {

    @Test
    public void testOriginalDijkstra() {
        List<Node<Integer>> graph = GraphGenerator.generateRandomGraph(100000);

        Dijkstra<Integer> dijkstra = new Dijkstra<>();
        long startTime = System.currentTimeMillis();
        dijkstra.calculateShortestPath(graph.get(0));
        long endTime = System.currentTimeMillis();

        System.out.println("Original Dijkstra Time: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testImprovedDijkstra() {
        List<Node<Integer>> graph = GraphGenerator.generateRandomGraph(1000000);

        ImprovedDijkstra<Integer> improvedDijkstra = new ImprovedDijkstra<>();
        long startTime = System.currentTimeMillis();
        improvedDijkstra.calculateShortestPath(graph.get(0));
        long endTime = System.currentTimeMillis();

        System.out.println("Improved Dijkstra Time: " + (endTime - startTime) + " ms");
    }
}
