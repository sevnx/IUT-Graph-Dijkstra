package graphe;

import arc.Arc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representation of a graph with a list of arcs.
 * @author Seweryn CZYKINOWSKI
 */
public class GrapheLArcs implements IGraphe {
    private static final String EMPTY_EDGE = "";
    private List<Arc> arcs;

    public GrapheLArcs() {
        arcs = new ArrayList<>();
    }

    public GrapheLArcs(String str){
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        arcs.add(new Arc(noeud));
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (contientArc(source,EMPTY_EDGE))
            oterArc(source,EMPTY_EDGE);
        if (contientArc(destination,EMPTY_EDGE))
            oterArc(destination,EMPTY_EDGE);
        arcs.add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        for (Arc arc : arcs)
            if (arc.getOrigin().equals(noeud) || arc.getDestination().equals(noeud)) {
                arcs.remove(arc);
                break;
            }
    }

    @Override
    public void oterArc(String source, String destination) {
        for (Arc arc : arcs)
            if (arc.getOrigin().equals(source) && arc.getDestination().equals(destination)) {
                arcs.remove(arc);
                break;
            }
    }

    @Override
    public List<String> getSommets() {
        List<String> nodes = new ArrayList<>();
        for (Arc arc : arcs) {
            if (!nodes.contains(arc.getOrigin()) && !arc.getOrigin().equals(Arc.EMPTY_EDGE))
                nodes.add(arc.getOrigin());
            if (!nodes.contains(arc.getDestination()) && !arc.getDestination().equals(Arc.EMPTY_EDGE))
                nodes.add(arc.getDestination());
        }
        Collections.sort(nodes);
        return nodes;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        for (Arc arc : arcs)
            if (Objects.equals(arc.getOrigin(), sommet) && !Objects.equals(arc.getDestination(), Arc.EMPTY_EDGE))
                succ.add(arc.getDestination());
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : arcs)
            if (arc.getOrigin().equals(src) && arc.getDestination().equals(dest))
                return arc.getValuation();
        throw new IllegalArgumentException();
    }

    @Override
    public boolean contientSommet(String sommet) {
        for (Arc arc : arcs)
            if (Objects.equals(arc.getOrigin(), sommet) || Objects.equals(arc.getDestination(), sommet))
                return true;
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : arcs)
            if (arc.getOrigin().equals(src) && arc.getDestination().equals(dest))
                return true;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        boolean first = false;
        for (Arc arc : arcs){
            if (first)
                sb.append(", ");
            else
                first=true;
            sb.append(arc);
        }
        return sb.toString();
    }
}
