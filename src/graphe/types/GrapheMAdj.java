package src.graphe.types;

import src.graphe.ArcListStringConverter;
import src.graphe.IGraphe;
import src.graphe.exceptions.*;

import java.util.*;

/**
 * Represents a graph with an adjacency matrix.
 *
 * @see IGraphe
 */
public class GrapheMAdj implements IGraphe {
    /**
     * Value of the self edge of an empty node, that signifies that the node doesn't have an outgoing edge.
     */
    private static final int EMPTY_NODE_SELF_EDGE = 0;
    /**
     * Value which indicates that there is no edge between two nodes.
     */
    private static final int NO_EDGE = Integer.MAX_VALUE;
    /**
     * Adjacency matrix representation of the graph.
     */
    private int[][] matrice;
    /**
     * Map of the indexes of the nodes in the adjacency matrix, matches a name to an index
     */
    private final Map<String, Integer> indices;

    /**
     * Default constructor of the class.
     * Creates an empty graph.
     */
    public GrapheMAdj() {
        indices = new HashMap<>();
        matrice = new int[0][0];
    }

    /**
     * Creates a graph from a string representation.
     *
     * @param str the string representation of the graph
     * @see IGraphe#peupler(String)
     */
    public GrapheMAdj(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            indices.put(noeud, matrice.length);
            int newSize = matrice.length + 1;
            int[][] newMatrice = new int[newSize][newSize];
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    if (i < newSize - 1 && j < newSize - 1)
                        newMatrice[i][j] = matrice[i][j];
                    else
                        newMatrice[i][j] = NO_EDGE;
                }
            }
            newMatrice[newSize - 1][newSize - 1] = EMPTY_NODE_SELF_EDGE;
            matrice = newMatrice;
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!contientSommet(source))
            ajouterSommet(source);
        if (!contientSommet(destination))
            ajouterSommet(destination);
        if (contientArc(source, source))
            oterArc(source, source);
        if (contientArc(source, destination))
            throw new ArcExistantException();
        if (source.isEmpty() || destination.isEmpty())
            throw new EmptySommetException();
        if (valeur < 0)
            throw new ArcValuationNegativeException();
        matrice[indices.get(source)][indices.get(destination)] = valeur;
    }
    
    @Override
    public void oterSommet(String noeud) {
        if (contientSommet(noeud)) {
            int index = indices.get(noeud);
            removeIndexFromMatrix(index);
        }
    }

    private void removeIndexFromMatrix(int index) {
        int newSize = matrice.length - 1;
        int[][] newMatrice = new int[newSize][newSize];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                if (i < index && j < index)
                    newMatrice[i][j] = matrice[i][j];
                else if (i < index)
                    newMatrice[i][j] = matrice[i][j + 1];
                else if (j < index)
                    newMatrice[i][j] = matrice[i + 1][j];
                else
                    newMatrice[i][j] = matrice[i + 1][j + 1];
            }
        }
        matrice = newMatrice;
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientArc(source, destination))
            matrice[indices.get(source)][indices.get(destination)] = NO_EDGE;
        else
            throw new ArcInexistantException();
    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>();
        if (indices == null)
            return sommets;
        sommets.addAll(indices.keySet());
        Collections.sort(sommets);
        return sommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        if (!contientSommet(sommet))
            throw new SommetInexistantException();
        List<String> succ = new ArrayList<>();
        Integer index = indices.get(sommet);
        for (int i = 0; i < matrice.length; ++i)
            if (matrice[index][i] != NO_EDGE)
                succ.add(getKeyFromValue(i));
        Collections.sort(succ);
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        if (!contientArc(src, dest))
            throw new ArcInexistantException();
        return matrice[indices.get(src)][indices.get(dest)];
    }

    @Override
    public boolean contientSommet(String sommet) {
        return indices.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (!contientSommet(src) || !contientSommet(dest))
            return false;
        return matrice[indices.get(src)][indices.get(dest)] != NO_EDGE;
    }

    /**
     * Utility method to get the key from a value in a map.
     *
     * @param value the value to search for
     * @return the key associated with the value, or null if the value is not found
     */
    private String getKeyFromValue(int value) {
        for (Map.Entry<String, Integer> entry : indices.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        throw new SommetInexistantException();
    }

    @Override
    public String toString() {
        List<String> arcs = new ArrayList<>();
        for (int i = 0; i < indices.size(); i++) {
            for (int j = 0; j < indices.size(); j++) {
                if (matrice[i][j] != NO_EDGE) {
                    if (matrice[i][j] == 0)
                        arcs.add(getKeyFromValue(i) + ":");
                    else
                        arcs.add(getKeyFromValue(i) + "-" + getKeyFromValue(j) + "(" + matrice[i][j] + ")");
                }
            }
        }
        return ArcListStringConverter.convertToString(arcs);
    }
}
