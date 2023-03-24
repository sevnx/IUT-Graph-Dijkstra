package graphe;

import arc.Arc;

import java.util.*;

public class GrapheHHadj implements IGraphe {
    /**
     * Adjacency list : map that matches a node name to a map
     * that matches a node name to the value of the edge between them.
     */
    private final Map<String, Map<String, Integer>> hhadj;

    /**
     * Default constructor of the class.
     * Creates an empty graph.
     */
    public GrapheHHadj() {
        hhadj = new HashMap<>();
    }

    /**
     * Creates a graph from a string representation.
     *
     * @param str the string representation of the graph
     * @see graphe.IGraphe#peupler(String)
     */
    public GrapheHHadj(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!hhadj.containsKey(noeud)) {
            hhadj.put(noeud, new HashMap<>());
            hhadj.get(noeud).put(Arc.EMPTY_EDGE, 0);
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (contientArc(source, Arc.EMPTY_EDGE))
            oterArc(source, Arc.EMPTY_EDGE);
        hhadj.get(source).put(destination, valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        hhadj.remove(noeud);
        for (Map<String, Integer> sommets : hhadj.values())
            sommets.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        hhadj.get(source).remove(destination);
    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>(hhadj.keySet());
        Collections.sort(sommets);
        return sommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>(hhadj.get(sommet).keySet());
        Collections.sort(succ);
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        return hhadj.get(src).get(dest);
    }

    @Override
    public boolean contientSommet(String sommet) {
        return hhadj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return hhadj.get(src).containsKey(dest);
    }

    @Override
    public String toString() {
        List<String> arcs = new ArrayList<>();
        for (String sommet : getSommets()) {
            for (String succ : hhadj.get(sommet).keySet()){
                if (succ.equals(Arc.EMPTY_EDGE))
                    arcs.add(sommet + ":");
                else
                    arcs.add(sommet + "-" + succ + "(" + hhadj.get(sommet).get(succ) + ")");
            }

        }
        return GrapheNoArc_StringGetter.getStringFromStringArcList(arcs);
    }
}
