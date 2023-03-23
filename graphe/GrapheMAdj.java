package graphe;

import java.util.*;

/**
 * Represents a graph with an adjacency matrix.
 */
public class GrapheMAdj implements IGraphe {
    private static final String EMPTY_EDGE = "";
    private static final int SELF_EDGE = 0;
    private static final int NO_EDGE = -1;
    private int[][] matrice;
    private final Map<String, Integer> indices;

    private String getKeyFromValue(int value){
        for (Map.Entry<String, Integer> entry : indices.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Creates an empty graph.
     */
    public GrapheMAdj() {
        indices = new HashMap<>();
        matrice = new int[0][0];
    }

    /**
     * Creates a graph from a string representation.
     * @param str the string representation of the graph
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
            // Add a new row and a new column, and fill them with NO_EDGE, and keep the old values.
            int[][] newMatrice = new int[newSize][newSize];
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    if (i < newSize - 1 && j < newSize - 1)
                        newMatrice[i][j] = matrice[i][j];
                    else
                        newMatrice[i][j] = NO_EDGE;
                }
            }
            newMatrice[newSize - 1][newSize - 1] = SELF_EDGE;
            matrice = newMatrice;
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!contientSommet(destination))
            ajouterSommet(destination);
        if (Objects.equals(destination, EMPTY_EDGE))
            destination=source;
        if (contientArc(source, source))
            oterArc(source, source);
        matrice[indices.get(source)][indices.get(destination)] = valeur;
    }

    @Override
    public void oterSommet(String noeud) {
        int index = indices.get(noeud);
        int newSize = matrice.length - 1;
        // Remove the row and the column, and keep the old values.
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
        matrice[indices.get(source)][indices.get(destination)] = NO_EDGE;
    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>();
        if (indices == null)
            return sommets;
        sommets.addAll(indices.keySet());
        return sommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        Integer index = indices.get(sommet);
        for (int i=0;i<matrice.length;++i)
            if (matrice[index][i]!=NO_EDGE)
                succ.add(getKeyFromValue(i));
        Collections.sort(succ);
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)];
    }

    @Override
    public boolean contientSommet(String sommet) {
        return indices.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)] != NO_EDGE;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        List<String> arcs = new ArrayList<>();
        for (int i = 0; i < indices.size(); i++) {
            for (int j = 0; j < indices.size(); j++) {
                if (matrice[i][j] != NO_EDGE) {
                    if (matrice[i][j] == 0)
                        arcs.add(getKeyFromValue(i)+":");
                    else
                        arcs.add(getKeyFromValue(i)+"-"+getKeyFromValue(j)+"("+matrice[i][j]+")");
                }
            }
        }
        Collections.sort(arcs);
        boolean first = true;
        for (String arc : arcs) {
            if (first) {
                str.append(arc);
                first = false;
            } else
                str.append(", ").append(arc);
        }
        return str.toString();
    }
}
