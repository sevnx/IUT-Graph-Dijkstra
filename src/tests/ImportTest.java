package src.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.ihm.GraphComparer;
import src.ihm.ListeFichiers;

import java.io.IOException;
import java.util.List;

import static src.ihm.ListeFichiers.Repertoires.*;

public class ImportTest {
    private static final List<String> listeFichiersSC = ListeFichiers.getListeFichiersSC();
    private static final List<String> listeFichiersAC = ListeFichiers.getListeFichiersAC();

    @Test
    void testDjikstraSansCircuit() throws NumberFormatException, IOException {
        for (String fichier : listeFichiersSC) {
            Assertions.assertTrue(GraphComparer.comparer(getPath(REPERTOIRE_ENONCE) + getPath(REPERTOIRE_SANS_CIRCUIT) + fichier,
                    getPath(REPERTOIRE_REPONSE) + getPath(REPERTOIRE_SANS_CIRCUIT) + fichier.replace('g', 'r')));
        }
    }

    @Test
    void testDjikstraCircuit() throws NumberFormatException, IOException {
        for (String fichier : listeFichiersAC) {
            Assertions.assertTrue(GraphComparer.comparer(getPath(REPERTOIRE_ENONCE) + getPath(REPERTOIRE_CIRCUIT) + fichier,
                    getPath(REPERTOIRE_REPONSE) + getPath(REPERTOIRE_CIRCUIT) + fichier.replace('g', 'r')));
        }
    }
}
