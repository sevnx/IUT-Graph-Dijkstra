package src.ihm;


import src.graphe.IGraphe;
import src.graphe.arc.Arc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class to import a graph from a file.
 */
public class GraphImporter {
    /**
     * Imports the response of a graph from a file.
     * The response contains the distance and the path result of shortest path algorithm.
     *
     * @param filePath path of the file
     * @param chemin   list of nodes to fill
     * @return the distance
     * @throws FileNotFoundException if the file is not found
     */
    public static int importerReponse(String filePath, List<Integer> chemin) throws FileNotFoundException {
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            sc.nextLine(); // nom de l'algo recommandé
            // distance attendue
            int distance = Integer.parseInt(sc.nextLine().trim());
            // chemin
            for (String s : sc.nextLine().split(" "))
                chemin.add(Integer.parseInt(s) - 1);
            return distance;
        }
    }

    /**
     * Imports a graph from a file.
     *
     * @param filepath path of the file
     * @param g        graph to import
     * @return the last arc imported (which is the shortest path source and destination to find)
     */
    public static Arc importer(String filepath, IGraphe g) {
        File file = new File(filepath);
        return importer(file, g);
    }

    /**
     * Imports a graph from a file.
     *
     * @param file file
     * @param g    graph to import
     * @return the last arc imported (which is the shortest path source and destination to find)
     */
    private static Arc importer(File file, IGraphe g) {
        try (Scanner sc = new Scanner(file)) {
            String line;
            if (!sc.hasNextLine()) {
                throw new IllegalArgumentException("Pas de src.graphe dans " + file);
            }
            sc.nextLine();
            Arc a = null;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                a = parse(line);
                if (sc.hasNextLine())
                    g.ajouterArc(a.getSource(), a.getDestination(), a.getValuation());
            }
            return a;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Pas de src.graphe dans " + file);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("valuation incorrecte dans " + file);
        }
    }

    /**
     * Parses a line of the file to an arc.
     *
     * @param string line of the file
     * @return constructed arc
     */
    public static Arc parse(String string) {
        String[] parts = string.split(" ");
        String source;
        int valuation;
        String destination;
        try {
            source = parts[0];
            valuation = Integer.parseInt(parts[1]);
            destination = parts[2];
        } catch (Exception e) {
            throw new IllegalArgumentException(string + " n'est pas un arc");
        }
        return new Arc(source, destination, valuation);
    }
}
