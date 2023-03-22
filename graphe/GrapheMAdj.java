package graphe;

import java.util.List;
import java.util.Map;

/**
 * Represents a graph with an adjacency matrix.
 */
public class GrapheMAdj implements IGraphe {
    private static final int NO_EDGE = -1;
    private int compteur;
    private int[][] matrice;
    private Map<String, Integer> indices;

    /**
     * Creates an empty graph.
     */
    public GrapheMAdj() {
        this("");
    }

    /**
     * Creates a graph from a string representation.
     * @param str the string representation of the graph
     */
    public GrapheMAdj(String str) {
    }

    public void addEdge(String origin, String destination, int valuation) {
        matrice[indices.get(origin)][indices.get(destination)] = valuation;
    }

    public void addNode(String node) {
        indices.put(node, compteur++);
    }

    @Override
    public void ajouterSommet(String noeud) {

    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {

    }

    @Override
    public void oterSommet(String noeud) {

    }

    @Override
    public void oterArc(String source, String destination) {

    }

    @Override
    public List<String> getSommets() {
        return null;
    }

    @Override
    public List<String> getSucc(String sommet) {
        return null;
    }

    @Override
    public int getValuation(String src, String dest) {
        return 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return false;
    }
}
