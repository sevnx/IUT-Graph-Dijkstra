package src.tests;


import org.junit.jupiter.api.Test;
import src.graphe.IGraphe;
import src.graphe.types.GrapheHHAdj;
import src.graphe.types.GrapheLAdj;
import src.graphe.types.GrapheLArcs;
import src.graphe.types.GrapheMAdj;
import src.pcc.PccDijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {
    private final IGraphe[] graphes = {
            new GrapheLArcs(), new GrapheLArcs(),
            new GrapheMAdj(), new GrapheHHAdj(), new GrapheLAdj()
    };

    private static void resetGraphe(IGraphe g) {
        for (String s : g.getSommets())
            g.oterSommet(s);
    }

    @Test
    void testAllGraphes() {
        for (IGraphe g : graphes) {
            test(g);
        }
    }

    void test(IGraphe g) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> pred = new HashMap<>();
        g.ajouterArc("A", "B", 2);
        g.ajouterArc("A", "E", 2);
        g.ajouterArc("A", "F", 1);
        g.ajouterArc("B", "C", 0);
        g.ajouterArc("B", "D", 4);
        g.ajouterArc("B", "E", 5);
        g.ajouterArc("C", "D", 1);
        g.ajouterArc("D", "E", 2);
        g.ajouterArc("F", "E", 2);
        PccDijkstra.dijkstra(g, "A", dist, pred);
        List<String> resAttendu = new ArrayList<>(List.of("A", "B", "C", "D"));
        assertEquals(resAttendu, PccDijkstra.chemin(dist, pred, "A", "D"));
        assertEquals(3, dist.get("D"));
        resetGraphe(g);
        g.ajouterArc("A", "C", 1);
        g.ajouterArc("C", "B", 1);
        g.ajouterArc("B", "A", 1);
        g.ajouterArc("A", "D", 2);
        PccDijkstra.dijkstra(g, "A", dist, pred);
        List<String> resAttendu2 = new ArrayList<>(List.of("A", "D"));
        assertEquals(resAttendu2, PccDijkstra.chemin(dist, pred, "A", "D"));
        assertEquals(2, dist.get("D"));
        resetGraphe(g);
        g.ajouterArc("A", "B", 1);
        g.ajouterArc("A", "C", 2);
        g.ajouterArc("B", "D", 3);
        g.ajouterArc("D", "E", 5);
        g.ajouterArc("C", "E", 4);
        PccDijkstra.dijkstra(g, "A", dist, pred);
        List<String> resAttendu3 = new ArrayList<>(List.of("A", "B", "D"));
        assertEquals(resAttendu3, PccDijkstra.chemin(dist, pred, "A", "D"));
        assertEquals(4, dist.get("D"));
    }
}
