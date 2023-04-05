package src.tests;


import org.junit.jupiter.api.Test;
import src.graphe.IGraphe;
import src.graphe.types.GrapheHHadj;
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
    @Test
    void Dijkstra_LArcs() {
        GrapheLArcs glarc = new GrapheLArcs();
        test(glarc);
    }

    @Test
    void Dijkstra_MAdj() {
        GrapheMAdj gmadj = new GrapheMAdj();
        test(gmadj);
    }

    @Test
    void Dijkstra_LAdj() {
        GrapheLAdj gladj = new GrapheLAdj();
        test(gladj);
    }

    @Test
    void Dijkstra_HHadj() {
        GrapheHHadj ghhadj = new GrapheHHadj();
        test(ghhadj);
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

        GrapheLArcs g1 = new GrapheLArcs();
        g1.ajouterArc("A", "C", 1);
        g1.ajouterArc("C", "B", 1);
        g1.ajouterArc("B", "A", 1);
        g1.ajouterArc("A", "D", 2);
        PccDijkstra.dijkstra(g1, "A", dist, pred);
        List<String> resAttendu2 = new ArrayList<>(List.of("A", "D"));
        assertEquals(resAttendu2, PccDijkstra.chemin(dist, pred, "A", "D"));
        assertEquals(2, dist.get("D"));


        GrapheLArcs g3 = new GrapheLArcs();
        g3.ajouterArc("A", "B", 1);
        g3.ajouterArc("A", "C", 2);
        g3.ajouterArc("B", "D", 3);
        g3.ajouterArc("D", "E", 5);
        g3.ajouterArc("C", "E", 4);
        PccDijkstra.dijkstra(g3, "A", dist, pred);
        List<String> resAttendu3 = new ArrayList<>(List.of("A", "B", "D"));
        assertEquals(resAttendu3, PccDijkstra.chemin(dist, pred, "A", "D"));
        assertEquals(4, dist.get("D"));
    }
}
