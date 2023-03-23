package graphe;

import arc.Arc;

import java.util.*;

import static arc.Arc.EMPTY_EDGE;

public class GrapheLAdj implements IGraphe {
    private final Map<String, List<Arc>> ladj;

    public GrapheLAdj() {
        ladj = new HashMap<>();
    }

    public GrapheLAdj(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!ladj.containsKey(noeud)){
            ladj.put(noeud, new ArrayList<>());
            ladj.get(noeud).add(new Arc(noeud));
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!ladj.containsKey(destination))
            ajouterSommet(destination);
        if (contientArc(source,EMPTY_EDGE))
            oterArc(source,EMPTY_EDGE);
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
        for (Arc arc : ladj.get(source))
            if (arc.getDestination().equals(destination)) {
                ladj.get(source).remove(arc);
                break;
            }
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(ladj.keySet());
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
        for (Arc arc : ladj.get(src))
            if (arc.getDestination().equals(dest))
                return true;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (List<Arc> arcs : ladj.values())
            for (Arc arc : arcs){
                if (!first)
                    sb.append(", ");
                else
                    first=false;
                sb.append(arc);
            }
        return sb.toString();
    }
}
