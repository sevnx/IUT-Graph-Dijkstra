package src.main.graphe.algos;

import src.main.graphe.core.IGrapheConst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Class that computes the shortest path between the source and the other vertices using the Dijkstra algorithm
 */
public final class Dijkstra {
    /** Infinity to represent the distance between two vertices that are not connected */
    private static final int INFINITY = Integer.MAX_VALUE;
    /** Graph that will be used to compute the shortest path */
    private final IGrapheConst graph;
    /** Distance between the source and the other vertices */
    private final Map<String, Integer> distance;
    /** Previous nodes for each node, in order to go back to the source */
    private final Map<String, String> previous;
    /** Priority queue to get the next node to visit */
    private final PriorityQueue<String> queue;
    /** State of each node, to know if it has been visited or not */
    private final Map<String, DijkstraState> state;

    private enum DijkstraState{
        NOT_VISITED, VISITED, VISITED_AND_PROCESSED
    }

    /**
     * Constructor of the Dijkstra class that initialize the data structures
     * @param graph Graph that will be used to compute the shortest path
     */
    private Dijkstra(IGrapheConst graph) {
        this.graph = graph;
        distance = new HashMap<>();
        previous = new HashMap<>();
        queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        state = new HashMap<>();
        for (String u : graph.getSommets()) {
            distance.put(u, INFINITY);
            previous.put(u, null);
            state.put(u, DijkstraState.NOT_VISITED);
        }
    }

    /**
     * Compute the shortest path between the source and the other vertices
     * @param graphe Graph that will be used to compute the shortest path
     * @param source Source node
     * @param dist [out] distance between the source and the other vertices
     * @param pred [out] previous nodes for each node, in order to go back to the source
     */
    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
        Dijkstra pcc = new Dijkstra(graphe);
        pcc.dijkstra(source);
        for (String u : graphe.getSommets()) {
            if (pcc.distance.get(u) == INFINITY)
                dist.put(u, -1);
            else
                dist.put(u, pcc.distance.get(u));
            pred.put(u, pcc.previous.get(u));
        }
    }

    /**
     * Decrease the key of the node u in the priority queue
     * @param u Node to decrease the key
     */
    private void decreaseKey(String u) {
        queue.remove(u);
        queue.add(u);
    }

    /**
     * Compute the shortest path between the source and the other vertices using the Dijkstra algorithm
     * Source : <a href="https://www.boost.org/doc/libs/1_39_0/libs/graph/doc/dijkstra_shortest_paths.html">Boost</a>
     * @param source Source node
     */
    private void dijkstra(String source) {
        distance.put(source, 0);
        queue.add(source);
        state.put(source, DijkstraState.VISITED);
        while (!queue.isEmpty()) {
            String u = queue.poll();
            for (String v : graph.getSucc(u)) {
                int w = graph.getValuation(u, v);
                if (distance.get(u) + w < distance.get(v)) {
                    distance.put(v, distance.get(u) + w);
                    previous.put(v, u);
                    if (state.get(v) == DijkstraState.NOT_VISITED) {
                        state.put(v, DijkstraState.VISITED);
                        queue.add(v);
                    } else if (state.get(v) == DijkstraState.VISITED) {
                        decreaseKey(v);
                    }
                }
            }
            state.put(u, DijkstraState.VISITED_AND_PROCESSED); // Visited and processed
        }
    }
}