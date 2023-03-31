package src.graphe.types;

import src.graphe.ArcListStringConverter;
import src.graphe.IGraphe;
import src.graphe.arc.Arc;
import src.graphe.exceptions.*;

import java.util.*;

/**
 * Representation of a graph with a hash map of hash maps.
 */
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
     * @see IGraphe#peupler(String)
     */
    public GrapheHHadj(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud))
            hhadj.put(noeud, new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (source.isEmpty() || destination.isEmpty())
            throw new EmptySommetException();
        if (!contientSommet(source))
            ajouterSommet(source);
        if (!contientSommet(destination))
            ajouterSommet(destination);
        if (contientArc(source, Arc.EMPTY_EDGE_DESTINATION))
            oterArc(source, Arc.EMPTY_EDGE_DESTINATION);
        else if (contientArc(source, destination))
            throw new ArcExistantException();
        if (valeur < 0)
            throw new ArcValuationNegativeException();
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
        if (!contientArc(source, destination))
            throw new ArcInexistantException();
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
        if (!contientSommet(sommet))
            throw new SommetInexistantException();
        List<String> succ = new ArrayList<>(hhadj.get(sommet).keySet());
        Collections.sort(succ);
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        if (!contientArc(src, dest))
            throw new ArcInexistantException();
        return hhadj.get(src).get(dest);
    }

    @Override
    public boolean contientSommet(String sommet) {
        return hhadj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (contientSommet(src))
            return hhadj.get(src).containsKey(dest);
        return false;
    }

    @Override
    public String toString() {
        List<String> arcs = new ArrayList<>();
        for (String sommet : getSommets()) {
            List<String> succs = getSucc(sommet);
            if (succs.isEmpty())
                arcs.add(sommet + ":");
            else {
                for (String succ : succs)
                    arcs.add(sommet + "-" + succ + "(" + hhadj.get(sommet).get(succ) + ")");
            }
        }
        return ArcListStringConverter.convertToString(arcs);
    }
}
