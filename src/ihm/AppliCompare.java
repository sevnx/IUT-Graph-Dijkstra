package src.ihm;

import java.io.FileNotFoundException;

import static src.ihm.ListeFichiers.getListeFichiersAC;
import static src.ihm.ListeFichiers.getListeFichiersSC;
import static src.ihm.ListeFichiers.Repertoires.*;

public class AppliCompare {
    public static void main(String[] args) throws FileNotFoundException {
        for (String fichier : getListeFichiersSC())
            GraphComparer.comparerVitesse(getPath(REPERTOIRE_ENONCE)+ getPath(REPERTOIRE_SANS_CIRCUIT) + fichier,
                    getPath(REPERTOIRE_REPONSE)+ getPath(REPERTOIRE_SANS_CIRCUIT) + fichier.replace('g', 'r'));
        for (String fichier : getListeFichiersAC())
            GraphComparer.comparerVitesse(getPath(REPERTOIRE_ENONCE)+ getPath(REPERTOIRE_CIRCUIT) + fichier,
                    getPath(REPERTOIRE_REPONSE)+ getPath(REPERTOIRE_CIRCUIT) + fichier.replace('g', 'r'));
    }
}
