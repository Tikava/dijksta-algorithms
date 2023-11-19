import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra<T> {

    public void calculateShortestPath(Node<T> source) {
        source.setDistance(0);
        Set<Node<T>> settledNodes = new HashSet<>();
        PriorityQueue<Node<T>> unsettledNodes = new PriorityQueue<>();
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node<T> currentNode = unsettledNodes.poll();
            for (Map.Entry<Node<T>, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node<T> adjacentNode = adjacencyPair.getKey();
                if (settledNodes.contains(adjacentNode)) {
                    continue;
                }
                evaluateDistanceAndPath(adjacentNode, adjacencyPair.getValue(), currentNode);
                if (!unsettledNodes.contains(adjacentNode)) {
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node<T> adjacentNode, Integer edgeWeight, Node<T> sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            LinkedList<Node<T>> newPath = new LinkedList<>(sourceNode.getShortestPath());
            newPath.add(sourceNode);
            adjacentNode.setShortestPath(newPath);
        }
    }

    public void printPaths(List<Node<T>> nodes) {
        for (Node<T> node : nodes) {
            String path = node.getShortestPath().stream()
                    .map(Node::getName)
                    .map(Objects::toString)
                    .collect(Collectors.joining(" -> "));
            System.out.println(path.isBlank() 
                ? String.format("%s : %s", node.getName(), node.getDistance())
                : String.format("%s -> %s : %s", path, node.getName(), node.getDistance()));
        }
    }
}
