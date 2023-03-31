package src.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.graphe.IGraphe;
import src.graphe.types.GrapheHHadj;
import src.graphe.types.GrapheLAdj;
import src.graphe.types.GrapheLArcs;
import src.graphe.types.GrapheMAdj;
import src.pcc.PccDijkstra;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DijkstraTest {

    private String[] toTab(String debut, String fin, PccDijkstra pcc) {
        ArrayList<String> path = pcc.chemin(debut, fin);
        String[] res = new String[path.size()];
        for (int i = 0; i < path.size(); ++i)
            res[i] = path.get(i);
        return res;
    }

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
        g.ajouterArc("A", "B", 2);
        g.ajouterArc("A", "E", 2);
        g.ajouterArc("A", "F", 1);
        g.ajouterArc("B", "C", 0);
        g.ajouterArc("B", "D", 4);
        g.ajouterArc("B", "E", 5);
        g.ajouterArc("C", "D", 1);
        g.ajouterArc("D", "E", 2);
        g.ajouterArc("F", "E", 2);
        PccDijkstra toto = new PccDijkstra(g);
        String[] resAttendu = {"A", "B", "C", "D"};
        assertArrayEquals(resAttendu, toTab("A", "D", toto));
        Assertions.assertEquals(3, toto.distance("A","D"));

        GrapheLArcs g1 = new GrapheLArcs();
        g1.ajouterArc("A", "C", 1);
        g1.ajouterArc("C", "B", 1);
        g1.ajouterArc("B", "A", 1);
        g1.ajouterArc("A", "D", 2);
        PccDijkstra toto1 = new PccDijkstra(g1);
        String[] resAttendu2 = {"A", "D"};
        assertArrayEquals(resAttendu2, toTab("A", "D", toto1));
        Assertions.assertEquals(2, toto1.distance("A","D"));


        GrapheLArcs g3 = new GrapheLArcs();
        g3.ajouterArc("A", "B", 1);
        g3.ajouterArc("A", "C", 2);
        g3.ajouterArc("B", "D", 3);
        g3.ajouterArc("D", "E", 5);
        g3.ajouterArc("C", "E", 4);
        PccDijkstra toto3 = new PccDijkstra(g3);
        String[] resAttendu3 = {"A", "B", "D"};
        assertArrayEquals(resAttendu3, toTab("A", "D", toto3));
        Assertions.assertEquals(4, toto3.distance("A","D"));
    }
}
