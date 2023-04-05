package src.ihm;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the list of files to be tested.
 */
public class ListeFichiers {
    /**
     * List of files without circuits.
     */
    private static final List<String> listeFichiersSC;
    /**
     * List of files with circuits.
     */
    private static final List<String> listeFichiersAC;

    static {
        listeFichiersSC = new ArrayList<>();
        listeFichiersSC.add("g-10-1.txt");
        listeFichiersSC.add("g-10-2.txt");
        listeFichiersSC.add("g-10-3.txt");
        listeFichiersSC.add("g-10-4.txt");
        listeFichiersSC.add("g-10-5.txt");
        listeFichiersSC.add("g-10-6.txt");
        listeFichiersSC.add("g-10-7.txt");
        listeFichiersSC.add("g-10-8.txt");
        listeFichiersSC.add("g-10-9.txt");
        listeFichiersSC.add("g-100-1.txt");
        listeFichiersSC.add("g-100-2.txt");
        listeFichiersSC.add("g-100-3.txt");
        listeFichiersSC.add("g-100-4.txt");

        listeFichiersAC = new ArrayList<>();
        listeFichiersAC.add("g-10-1.txt");
        listeFichiersAC.add("g-10-2.txt");
        listeFichiersAC.add("g-10-3.txt");
        listeFichiersAC.add("g-10-4.txt");
        listeFichiersAC.add("g-10-5.txt");
        listeFichiersAC.add("g-10-6.txt");
        listeFichiersAC.add("g-10-7.txt");
        listeFichiersAC.add("g-10-8.txt");
        listeFichiersAC.add("g-10-9.txt");
        listeFichiersAC.add("g-100-1.txt");
        listeFichiersAC.add("g-100-2.txt");
        listeFichiersAC.add("g-100-3.txt");
        listeFichiersAC.add("g-100-4.txt");
    }

    /**
     * Returns the list of files without circuits.
     * @return the list of files without circuits
     */
    public static ArrayList<String> getListeFichiersSC() {
        return new ArrayList<>(listeFichiersSC);
    }

    /**
     * Returns the list of files with circuits.
     * @return the list of files with circuits
     */
    public static ArrayList<String> getListeFichiersAC() {
        return new ArrayList<>(listeFichiersAC);
    }

    /**
     * Enum containing the paths to the files.
     */
    public enum Repertoires {
        REPERTOIRE_ENONCE("graphes/"),
        REPERTOIRE_REPONSE("reponses/"),
        REPERTOIRE_CIRCUIT("ac/"),
        REPERTOIRE_SANS_CIRCUIT("sc/");

        private final String path;

        Repertoires(String path) {
            this.path = path;
        }

        public static String getPath(Repertoires r) {
            return r.path;
        }
    }
}

