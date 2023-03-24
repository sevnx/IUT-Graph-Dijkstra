package pcc;

import java.util.ArrayList;

public interface IPCC {
    /**
     * Returns the shortest distance between 2 nodes
     * @param first, the first node
     * @param second, the second node
     * @return the distance between the 2 nodes
     */
    int distance(String first, String second);

    /**
     * Returns the shortest path between 2 nodes
     * @param first the first node
     * @param second the second node
     * @param path the path between the 2 nodes
     * @return the shortest path between the 2 nodes
     */
    int getPath(String first, String second, ArrayList<String> path);

    /**
     * Tests if you can apply the algorithm
     * @return true if you can apply the algorithm, false otherwise
     */
    boolean canApplyAlgorithm();
}

