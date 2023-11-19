import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A class representing a node in a graph. It supports generic types for node names,
 * and is used in pathfinding algorithms like Dijkstra's.
 *
 * @param <T> the type of the node identifier
 */
public class Node<T> implements Comparable<Node<T>> {

    private final T name;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node<T>> shortestPath = new LinkedList<>();
    private Map<Node<T>, Integer> adjacentNodes = new HashMap<>();

    public Node(T name) {
        if (name == null) {
            throw new IllegalArgumentException("Node name cannot be null");
        }
        this.name = name;
    }

    public T getName() {
        return this.name;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer distance) {
        if (distance == null || distance < 0) {
            throw new IllegalArgumentException("Distance cannot be null or negative");
        }
        this.distance = distance;
    }

    public List<Node<T>> getShortestPath() {
        return new LinkedList<>(this.shortestPath); 
    }

    public void setShortestPath(List<Node<T>> shortestPath) {
        if (shortestPath == null) {
            throw new IllegalArgumentException("Shortest path cannot be null");
        }
        this.shortestPath = new LinkedList<>(shortestPath); 
    }

    public Map<Node<T>, Integer> getAdjacentNodes() {
        return new HashMap<>(this.adjacentNodes); 
    }

    public void addAdjacentNode(Node<T> node, int weight) {
        if (node == null) {
            throw new IllegalArgumentException("Adjacent node cannot be null");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node<T> other) {
        return Integer.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return "Node{" +
               "name=" + name +
               ", distance=" + distance +
               ", adjacentNodes=" + adjacentNodes.keySet() +
               '}';
    }
}