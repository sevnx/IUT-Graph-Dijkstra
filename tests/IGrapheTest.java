package tests;


import graphe.IGraphe;
import graphe.arc.Arc;
import graphe.ihm.GraphImporter;
import graphe.types.GrapheHHadj;
import graphe.types.GrapheLAdj;
import graphe.types.GrapheLArcs;
import graphe.types.GrapheMAdj;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IGrapheTest {
    // graphe de l'exercice 3.1 du poly de maths
    // avec en plus un noeud isole : J
    private final String g31 =
            "A-C(2), A-D(1), "
                    + "B-G(3), "
                    + "C-H(2), "
                    + "D-B(3), D-C(5), D-E(3), "
                    + "E-C(1), E-G(3), E-H(7), "
                    + "F:, "
                    + "G-B(2), G-F(1), "
                    + "H-F(4), H-G(2), "
                    + "I-H(10), "
                    + "J:";

    @Test
    void exo3_1Maths_LArcs() {
        GrapheLArcs glarc = new GrapheLArcs(g31);
        tester3_1(glarc);
    }

    @Test
    void exo3_1Maths_MAdj() {
        GrapheMAdj gmadj = new GrapheMAdj(g31);
        tester3_1(gmadj);
    }

    @Test
    void exo3_1Maths_LAdj() {
        GrapheLAdj gladj = new GrapheLAdj(g31);
        tester3_1(gladj);
    }

    @Test
    void exo3_1Maths_HHadj() {
        GrapheHHadj ghhadj = new GrapheHHadj(g31);
        tester3_1(ghhadj);
    }

    void tester3_1(IGraphe g) {
        List<String> sommets_exp = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        List<String> sommets = new ArrayList<>(g.getSommets()); // pas forcement triee
        Collections.sort(sommets);
        assertEquals(sommets_exp, g.getSommets());
        assertTrue(g.contientSommet("C"));
        assertFalse(g.contientSommet("c"));
        assertTrue(g.contientArc("C", "H"));
        assertFalse(g.contientArc("H", "C"));
        assertEquals(7, g.getValuation("E", "H"));
        List<String> successeurs = new ArrayList<>(g.getSucc("D")); // pas forcement triee
        Collections.sort(successeurs);
        assertEquals(List.of("B", "C", "E"), successeurs);
        assertEquals(g31, g.toString());

        g.ajouterSommet("A"); // ne fait rien car A est deja present
        assertEquals(g31, g.toString());
        assertThrows(IllegalArgumentException.class,
                () -> g.ajouterArc("G", "B", 1));        // deja present
        g.oterSommet("X"); // ne fait rien si le sommet n'est pas present
        assertEquals(g31, g.toString());
        assertThrows(IllegalArgumentException.class,
                () -> g.oterArc("X", "Y"));  // n'existe pas

        assertThrows(IllegalArgumentException.class,
                () -> g.ajouterArc("A", "B", -1)); // valuation negative
    }

    @Test
    void importer() throws NumberFormatException, FileNotFoundException {
        IGraphe g = new GrapheLArcs();
        Arc a = GraphImporter.importer("graphes/ac/g-10-1.txt", g);
        assertEquals(g.toString(), "1-3(5), "
                + "10-3(3), 2-1(5), 2-3(5), 2-5(4), "
                + "3-4(4), 3-5(4), 4-10(1), 4-2(1), 4-7(3), "
                + "5-9(4), 6-2(3), 6-3(4), 7-3(2),"
                + " 8-2(4), 8-6(1), 9-2(4)");
        assertEquals("5", a.getSource());
        assertEquals("7", a.getDestination());
    }
}
