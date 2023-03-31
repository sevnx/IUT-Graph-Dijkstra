package src.ihm;

import src.graphe.IGrapheConst;
import src.graphe.arc.Arc;
import src.graphe.types.GrapheHHadj;
import src.graphe.types.GrapheLAdj;
import src.graphe.types.GrapheLArcs;
import src.graphe.types.GrapheMAdj;
import src.pcc.PccDijkstra;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GraphComparer {
    public static boolean comparer(String fichierEnonce, String fichierReponse) throws FileNotFoundException {
        GrapheLArcs gLArcs = new GrapheLArcs();
        Arc df = GraphImporter.importer(fichierEnonce, gLArcs);
        PccDijkstra algo = new PccDijkstra(gLArcs);
        int distanceCalculee = algo.distance(df.getSource(), df.getDestination());
        int distanceAttendue = GraphImporter.importerReponse(fichierReponse, new ArrayList<>());
        if (distanceCalculee != distanceAttendue) {
            System.out.println("Distance attendue : " + distanceAttendue);
            System.out.println("Distance calculée : " + distanceCalculee);
            return false;
        }
        return true;
    }

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
        System.out.println("LArcs : " + getTimeInSecondsFromFloat(endTimeLArcs,startTimeLArcs) +" / " + getExecutionTime(gLArcs, df, distance));
        System.out.println("HHadj : " + getTimeInSecondsFromFloat(endTimeHHadj,endTimeLArcs) +" / " + getExecutionTime(gHHadj, df2, distance));
        System.out.println("LAdj : " + getTimeInSecondsFromFloat(endTimeLAdj,endTimeHHadj) +" / " + getExecutionTime(gLAdj, df3, distance));
        System.out.println("MAdj : " + getTimeInSecondsFromFloat(endTimeMAdj,endTimeLAdj) +" / " +getExecutionTime(gMAdj, df4, distance));
    }

    public static float getExecutionTime(IGrapheConst g, Arc df, int distance) {
        long startTime = System.nanoTime();
        PccDijkstra algo = new PccDijkstra(g);
        algo.chemin(df.getSource(), df.getDestination());
        if (algo.distance(df.getSource(), df.getDestination()) != distance)
            throw new IllegalArgumentException("Distance attendue : " + distance + " Distance calculée : " + algo.distance(df.getSource(), df.getDestination()));
        long endTime = System.nanoTime();
        return getTimeInSecondsFromFloat(endTime, startTime);
    }

    private static float getTimeInSecondsFromFloat(long endTime, long startTime) {
        return (float) (endTime - startTime) / 1000000;
    }
}
