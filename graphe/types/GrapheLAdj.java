package graphe.types;

import graphe.IGraphe;
import graphe.arc.Arc;

import java.util.*;

import static graphe.arc.Arc.EMPTY_EDGE;

/**
 * Representation of a graph with an adjacency list.
 *
 * @see graphe.IGraphe
 */
public class GrapheLAdj implements IGraphe {
    /**
     * Adjacency list : map that matches a node name to the list of their outgoing arcs.
     *
     * @see graphe.arc.Arc
     */
    private final Map<String, List<Arc>> ladj;

    /**
     * Default constructor of the class.
     * Creates an empty graph.
     */
    public GrapheLAdj() {
        ladj = new HashMap<>();
    }

    /**
     * Creates a graph from a string representation.
     *
     * @param str the string representation of the graph
     * @see graphe.IGraphe#peupler(String)
     */
    public GrapheLAdj(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!ladj.containsKey(noeud)) {
            ladj.put(noeud, new ArrayList<>());
            ladj.get(noeud).add(new Arc(noeud));
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!ladj.containsKey(source))
            ajouterSommet(source);
        if (!ladj.containsKey(destination))
            ajouterSommet(destination);
        if (contientArc(source, EMPTY_EDGE))
            oterArc(source, EMPTY_EDGE);
        if (contientArc(source, destination))
            throw new IllegalArgumentException("Arc existant.");
        ladj.get(source).add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        ladj.remove(noeud);
        for (List<Arc> arcs : ladj.values())
            for (Arc arc : arcs)
                if (arc.getDestination().equals(noeud)) {
                    arcs.remove(arc);
                    break;
                }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (!contientArc(source, destination))
            throw new IllegalArgumentException("Arc inexistant.");
        for (Arc arc : ladj.get(source))
            if (arc.getDestination().equals(destination)) {
                ladj.get(source).remove(arc);
                break;
            }
    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>(ladj.keySet());
        Collections.sort(sommets);
        return sommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        for (Arc arc : ladj.get(sommet))
            succ.add(arc.getDestination());
        Collections.sort(succ);
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : ladj.get(src))
            if (arc.getDestination().equals(dest))
                return arc.getValuation();
        throw new IllegalArgumentException();
    }

    @Override
    public boolean contientSommet(String sommet) {
        return ladj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (!contientSommet(src))
            return false;
        for (Arc arc : ladj.get(src))
            if (arc.getDestination().equals(dest))
                return true;
        return false;
    }

    @Override
    public String toString() {
        List<String> arcsString = new ArrayList<>();
        for (List<Arc> arcs : ladj.values())
            for (Arc arc : arcs)
                arcsString.add(arc.toString());
        return ArcListStringConverter.convertToString(arcsString);
    }
}
