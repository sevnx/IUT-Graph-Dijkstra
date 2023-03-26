package tests;


import graphe.types.GrapheLArcs;
import org.junit.jupiter.api.Test;
import pcc.PCCDijkstra;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DijkstraTest {

    private String[] toTab(String entrant, String sortant, PCCDijkstra pcc) {
        ArrayList<String> path = pcc.chemin(entrant, sortant);
        String[] res = new String[path.size()];
        for (int i = 0; i < path.size(); ++i)
            res[i] = path.get(i);
        return res;
    }


    @Test
    void test() {
        GrapheLArcs g = new GrapheLArcs();
        g.ajouterArc("A", "B", 2);
        g.ajouterArc("A", "E", 2);
        g.ajouterArc("A", "F", 1);
        g.ajouterArc("B", "C", 0);
        g.ajouterArc("B", "D", 4);
        g.ajouterArc("B", "E", 5);
        g.ajouterArc("C", "D", 1);
        g.ajouterArc("D", "E", 2);
        g.ajouterArc("F", "E", 2);
        PCCDijkstra toto = new PCCDijkstra(g);
        String[] resAttendu = {"A", "B", "C", "D"};
        assertArrayEquals(resAttendu, toTab("A", "D", toto));

        GrapheLArcs g1 = new GrapheLArcs();
        g1.ajouterArc("A", "C", 1);
        g1.ajouterArc("C", "B", 1);
        g1.ajouterArc("B", "A", 1);
        g1.ajouterArc("A", "D", 2);
        PCCDijkstra toto1 = new PCCDijkstra(g1);
        String[] resAttendu2 = {"A", "D"};
        assertArrayEquals(resAttendu2, toTab("A", "D", toto1));

        GrapheLArcs g3 = new GrapheLArcs();
        g3.ajouterArc("A", "B", 1);
        g3.ajouterArc("A", "C", 2);
        g3.ajouterArc("B", "D", 3);
        g3.ajouterArc("D", "E", 5);
        g3.ajouterArc("C", "E", 4);
        PCCDijkstra toto3 = new PCCDijkstra(g3);
        String[] resAttendu3 = {"A", "B", "D"};
        assertArrayEquals(resAttendu3, toTab("A", "D", toto3));
    }
}
