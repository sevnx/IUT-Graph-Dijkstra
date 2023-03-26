package graphe.types;

import graphe.IGraphe;
import graphe.arc.Arc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static graphe.arc.Arc.EMPTY_EDGE;

/**
 * Representation of a graph with a list of arcs.
 *
 * @see graphe.IGraphe
 */
public class GrapheLArcs implements IGraphe {
    /**
     * List of arcs
     *
     * @see graphe.arc.Arc
     */
    private final List<Arc> arcs;

    /**
     * Default constructor of the class.
     * Creates an empty graph.
     */
    public GrapheLArcs() {
        arcs = new ArrayList<>();
    }

    /**
     * Creates a graph from a string representation.
     *
     * @param str the string representation of the graph
     * @see graphe.IGraphe#peupler(String)
     */
    public GrapheLArcs(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud))
            arcs.add(new Arc(noeud));
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (contientArc(source, destination))
            throw new IllegalArgumentException("Arc existant.");
        if (contientArc(source, EMPTY_EDGE))
            oterArc(source, EMPTY_EDGE);
        arcs.add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        for (Arc arc : arcs)
            if (arc.getSource().equals(noeud) || arc.getDestination().equals(noeud)) {
                arcs.remove(arc);
                break;
            }
    }

    @Override
    public void oterArc(String source, String destination) {
        boolean found = false;
        for (Arc arc : arcs)
            if (arc.getSource().equals(source) && arc.getDestination().equals(destination)) {
                arcs.remove(arc);
                found = true;
                break;
            }
        if (!found)
            throw new IllegalArgumentException("Arc inexistant");
    }

    @Override
    public List<String> getSommets() {
        List<String> nodes = new ArrayList<>();
        for (Arc arc : arcs) {
            if (!nodes.contains(arc.getSource()) && !arc.getSource().equals(EMPTY_EDGE))
                nodes.add(arc.getSource());
            if (!nodes.contains(arc.getDestination()) && !arc.getDestination().equals(EMPTY_EDGE))
                nodes.add(arc.getDestination());
        }
        Collections.sort(nodes);
        return nodes;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        for (Arc arc : arcs)
            if (Objects.equals(arc.getSource(), sommet) && !Objects.equals(arc.getDestination(), EMPTY_EDGE))
                succ.add(arc.getDestination());
        Collections.sort(succ);
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : arcs)
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest))
                return arc.getValuation();
        throw new IllegalArgumentException();
    }

    @Override
    public boolean contientSommet(String sommet) {
        for (Arc arc : arcs)
            if (Objects.equals(arc.getSource(), sommet) || Objects.equals(arc.getDestination(), sommet))
                return true;
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : arcs)
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest))
                return true;
        return false;
    }

    @Override
    public String toString() {
        List<String> arcsString = new ArrayList<>();
        for (Arc arc : arcs) {
            arcsString.add(arc.toString());
        }
        return ArcListStringConverter.convertToString(arcsString);
    }
}
