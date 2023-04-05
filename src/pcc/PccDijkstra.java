package src.pcc;

import src.graphe.IGrapheConst;

import java.util.*;

/**
 * Computes the shortest path between two nodes in a graph.
 * The algorithm used is Dijkstra's algorithm.
 */
public class PccDijkstra {
    /**
     * Infinite distance representing no path.
     */
    private static final int INFINITE = Integer.MAX_VALUE;
    /**
     * Graph to apply Dijkstra's algorithm on
     */
    private final IGrapheConst graph;
    /**
     * Queue of nodes to be processed
     */
    private final Set<String> queue;
    /**
     * Distance from the source node to each node
     */
    private final Map<String, Integer> distance;
    /**
     * Previous node in the shortest path to each node
     */
    private final Map<String, String> previous;

    /**
     * Creates a new instance of Dijkstra's algorithm.
     *
     * @param graph graph to apply Dijkstra's algorithm on
     */
    public PccDijkstra(IGrapheConst graph) {
        this.graph = graph;
        queue = new HashSet<>();
        distance = new HashMap<>();
        previous = new HashMap<>();
    }

    /**
     * Djikstra's algorithm, computes the shortest path between a source node and all other nodes in a graph.
     *
     * @param graphe graph to apply Dijkstra's algorithm on
     * @param source source node to compute the shortest path from
     * @param dist   distance from the source node to each node
     * @param pred   previous node in the shortest path to each node
     */
    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
        PccDijkstra pcc = new PccDijkstra(graphe);
        pcc.initAlgorithm(source);
        while (!pcc.queue.isEmpty()) {
            String u = pcc.getMin();
            if (u == null)
                break;
            pcc.queue.remove(u);
            for (String v : graphe.getSucc(u))
                pcc.updateLength(u, v);
        }
        dist.clear();
        pred.clear();
        for (String node : pcc.distance.keySet())
            if (pcc.distance.get(node) != INFINITE) {
                dist.put(node, pcc.distance.get(node));
                pred.put(node, pcc.previous.get(node));
            }
    }

    /**
     * Returns the shortest path between two nodes, by following the predecessors and reversing the list at the end.
     *
     * @param dist        distance from the source node to each node (output of Dijkstra's algorithm)
     * @param pred        previous node in the shortest path to each node (output of Dijkstra's algorithm)
     * @param source      source node
     * @param destination destination node
     * @return shortest path between the source and destination nodes
     */
    public static List<String> chemin(Map<String, Integer> dist, Map<String, String> pred, String source, String destination) {
        List<String> chemin = new ArrayList<>();
        String node = destination;
        if (dist.get(node) == INFINITE)
            return null;
        chemin.add(node);
        while (node != null && !node.equals(source)) {
            node = pred.get(node);
            if (node != null)
                chemin.add(node);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    /**
     * Initializes the algorithm.
     *
     * @param source source node
     */
    private void initAlgorithm(String source) {
        for (String node : graph.getSommets()) {
            distance.put(node, INFINITE);
            previous.put(node, null);
            queue.add(node);
        }
        distance.put(source, 0);
    }

    /**
     * Returns the node with the minimum distance from the source node.
     *
     * @return node with the minimum distance from the source node
     */
    private String getMin() {
        String minNode = null;
        int min = INFINITE;
        for (String node : queue) {
            if (distance.get(node) < min) {
                min = distance.get(node);
                minNode = node;
            }
        }
        return minNode;
    }

    /**
     * Updates the distance from the source node to the node v if the distance from the source node to the node u is shorter.
     *
     * @param u node to update from
     * @param v node to update (if the distance from the source node to the node u is shorter)
     */
    private void updateLength(String u, String v) {
        int alt = distance.get(u) + graph.getValuation(u, v);
        if (alt < distance.get(v)) {
            distance.put(v, alt);
            previous.put(v, u);
        }
    }
}
