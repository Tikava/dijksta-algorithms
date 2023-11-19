import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {

    public static List<Node<Integer>> generateRandomGraph(int numberOfNodes) {
        List<Node<Integer>> nodes = new ArrayList<>();

        // Creating nodes
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node<>(i));
        }

        // Creating random edges
        Random random = new Random();
        for (Node<Integer> node : nodes) {
            int edges = random.nextInt(10) + 1; // Each node will have 1 to 10 random edges
            for (int i = 0; i < edges; i++) {
                Node<Integer> targetNode = nodes.get(random.nextInt(numberOfNodes));
                int weight = random.nextInt(100) + 1; // Edge weight between 1 and 100
                node.addAdjacentNode(targetNode, weight);
            }
        }

        return nodes;
    }
}
