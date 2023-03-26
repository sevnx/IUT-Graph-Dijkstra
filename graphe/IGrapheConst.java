package graphe;

import java.util.List;

/**
 * Interface for constant graphs.
 */
public interface IGrapheConst {
    /**
     * Returns the list of nodes.
     *
     * @return the list of nodes
     */
    List<String> getSommets();

    /**
     * Returns the list of edges.
     *
     * @param sommet the node
     * @return the list of edges
     */
    List<String> getSucc(String sommet);

    /**
     * Returns the value of an edge.
     *
     * @param src  the source node
     * @param dest the destination node
     * @return the value of the edge
     */
    int getValuation(String src, String dest);

    /**
     * Verifies if a node is in the graph.
     *
     * @param sommet the node
     * @return true if the node is in the graph, false otherwise
     */
    boolean contientSommet(String sommet);

    /**
     * Verifies if an edge is in the graph.
     *
     * @param src  the source node
     * @param dest the destination node
     * @return true if the edge is in the graph, false otherwise
     */
    boolean contientArc(String src, String dest);
}
