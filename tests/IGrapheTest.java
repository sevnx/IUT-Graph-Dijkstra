package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import graphe.GrapheLAdj;
import graphe.GrapheLArcs;
import graphe.GrapheMAdj;
import graphe.IGraphe;
import org.junit.jupiter.api.Test;

class IGrapheTest {
	// graphe de l'exercice 3.1 du poly de maths
	// avec en plus un noeud isole : J
	private String g31 = 
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
	void exo3_1Maths() {
		GrapheLArcs glarc = new GrapheLArcs(g31);
		GrapheMAdj gmadj = new GrapheMAdj(g31);
		GrapheLAdj gladj = new GrapheLAdj(g31);
		tester3_1(glarc);
		tester3_1(gmadj);
		tester3_1(gladj);
	}

	void tester3_1(IGraphe g) {
		List<String> sommets = List.of("A","B","C","D","E","F","G","H","I","J");
		assertEquals(sommets, g.getSommets());
		assertTrue(g.contientSommet("C"));
		assertFalse(g.contientSommet("c"));
		assertTrue(g.contientArc("C","H"));
		assertFalse(g.contientArc("H","C"));
		assertEquals(7,g.getValuation("E", "H"));
		assertEquals(List.of("B","C", "E"), g.getSucc("D"));
		assertEquals(g31, g.toString());
	}

}
