package src.pcc;

import src.graphe.IGrapheConst;

import java.util.*;

/**
 * Computes the shortest path between two nodes in a graph.
 * The algorithm used is Dijkstra's algorithm.
 */
public class PccDjikstra {
    /**
     * Infinite distance representing no path.
     */
    private static final int INFINITE = Integer.MAX_VALUE;
    /**
     * Graph to apply Dijkstra's algorithm on
     */
    private final IGrapheConst graph;
    /**
     * Queue of nodes
     */
    private final Set<String> queue;
    /**
     *
     */
    private final Map<String, Integer> distance;
    private final Map<String, String> previous;

    private PccDjikstra(IGrapheConst graph) {
        this.graph = graph;
        queue = new HashSet<>();
        distance = new HashMap<>();
        previous = new HashMap<>();
    }

    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
        PccDjikstra pcc = new PccDjikstra(graphe);
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

    private void initAlgorithm(String source) {
        for (String node : graph.getSommets()) {
            distance.put(node, INFINITE);
            previous.put(node, null);
            queue.add(node);
        }
        distance.put(source, 0);
    }

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

    private void updateLength(String u, String v) {
        int alt = distance.get(u) + graph.getValuation(u, v);
        if (alt < distance.get(v)) {
            distance.put(v, alt);
            previous.put(v, u);
        }
    }
}
