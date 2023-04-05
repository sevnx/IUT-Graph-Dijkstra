package src.ihm;

import src.graphe.IGrapheConst;
import src.graphe.arc.Arc;
import src.graphe.types.GrapheHHadj;
import src.graphe.types.GrapheLAdj;
import src.graphe.types.GrapheLArcs;
import src.graphe.types.GrapheMAdj;
import src.pcc.PccDjikstra;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility graph to compare results of Dijkstra's algorithm and the speeds of construction / execution of graphs
 * and the application of Dijkstra's algorithm
 */
public class GraphComparer {
    /**
     * Compare the results of Dijkstra's algorithm on a graph compared to the expected results
     *
     * @param fichierEnonce  file containing the graph
     * @param fichierReponse file containing the expected result
     * @return true if the results are the same
     * @throws FileNotFoundException if the file is not found
     */
    public static boolean comparer(String fichierEnonce, String fichierReponse) throws FileNotFoundException {
        GrapheLArcs gLArcs = new GrapheLArcs();
        Arc df = GraphImporter.importer(fichierEnonce, gLArcs);
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecesseurs = new HashMap<>();
        PccDjikstra.dijkstra(gLArcs, df.getSource(), distances, predecesseurs);
        int distanceAttendue = GraphImporter.importerReponse(fichierReponse, new ArrayList<>());
        int distanceCalculee = distances.get(df.getDestination());
        if (distanceCalculee != distanceAttendue)
            throw new RuntimeException("Distance attendue : " + distanceAttendue + "/" + "Distance calculée : " + distanceCalculee);
        return true;
    }

    /**
     * Compare the speed of construction and execution of Dijkstra's algorithm on all the representations of a graph
     *
     * @param fichierEnonce  file containing the graph
     * @param fichierReponse file containing the expected result
     * @throws FileNotFoundException if the file is not found
     */
    public static void comparerVitesse(String fichierEnonce, String fichierReponse) throws FileNotFoundException {
        int distance = GraphImporter.importerReponse(fichierReponse, new ArrayList<>());
        System.out.println("Comparaison de vitesse (création du graphe / calcul du chemin le plus court)");
        System.out.println("Distance attendue : " + distance);
        long startTimeLArcs = System.nanoTime();
        GrapheLArcs gLArcs = new GrapheLArcs();
        Arc df = GraphImporter.importer(fichierEnonce, gLArcs);
        long endTimeLArcs = System.nanoTime();
        GrapheHHadj gHHadj = new GrapheHHadj();
        Arc df2 = GraphImporter.importer(fichierEnonce, gHHadj);
        long endTimeHHadj = System.nanoTime();
        GrapheLAdj gLAdj = new GrapheLAdj();
        Arc df3 = GraphImporter.importer(fichierEnonce, gLAdj);
        long endTimeLAdj = System.nanoTime();
        GrapheMAdj gMAdj = new GrapheMAdj();
        Arc df4 = GraphImporter.importer(fichierEnonce, gMAdj);
        long endTimeMAdj = System.nanoTime();
        System.out.println("S : " + df.getSource() + ", D : " + df.getDestination());
        System.out.println("LArcs, Construction : " + getTimeDifferenceInSecondsFromNanoTime(endTimeLArcs, startTimeLArcs)
                + " / Execution : " + getDijkstraExecutionTime(gLArcs, df, distance));
        System.out.println("HHadj, Construction : " + getTimeDifferenceInSecondsFromNanoTime(endTimeHHadj, endTimeLArcs)
                + " / Execution : " + getDijkstraExecutionTime(gHHadj, df2, distance));
        System.out.println("LAdj, Construction : " + getTimeDifferenceInSecondsFromNanoTime(endTimeLAdj, endTimeHHadj)
                + " / Execution : " + getDijkstraExecutionTime(gLAdj, df3, distance));
        System.out.println("MAdj, Construction : " + getTimeDifferenceInSecondsFromNanoTime(endTimeMAdj, endTimeLAdj)
                + " / Execution : " + getDijkstraExecutionTime(gMAdj, df4, distance));
    }

    /**
     * Get the execution time of Dijkstra's algorithm on a graph
     *
     * @param g        graph
     * @param df       arc with the source and destination which is the path to find
     * @param distance expected distance
     * @return execution time in nanoseconds
     */
    public static float getDijkstraExecutionTime(IGrapheConst g, Arc df, int distance) {
        long startTime = System.nanoTime();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecesseurs = new HashMap<>();
        PccDjikstra.dijkstra(g, df.getSource(), distances, predecesseurs);
        if (distances.get(df.getDestination()) != distance)
            throw new IllegalStateException("Distance attendue : " + distance + " Distance calculée : " + distances.get(df.getDestination()));
        long endTime = System.nanoTime();
        return getTimeDifferenceInSecondsFromNanoTime(endTime, startTime);
    }

    /**
     * Get the time difference in seconds from nanoseconds
     *
     * @param endTime   end time in nanoseconds
     * @param startTime start time in nanoseconds
     * @return time difference in seconds
     */
    private static float getTimeDifferenceInSecondsFromNanoTime(long endTime, long startTime) {
        return (float) (endTime - startTime) / 1000000;
    }
}
