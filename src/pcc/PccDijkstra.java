package src.pcc;

import src.graphe.IGrapheConst;

import java.util.*;

/**
 * Class that represents the Dijkstra algorithm.
 * It finds the shortest path between two nodes in a graph.
 */
public class PccDijkstra {
    /** Nodes of the graph for the Dijkstra algorithm. */
    private final Map<String, Node> nodes;
    /** Graph to apply the Dijkstra algorithm on. */
    IGrapheConst graph;

    /**
     * Default constructor of the class.
     * @param graph to apply the Dijkstra algorithm on
     */
    public PccDijkstra(IGrapheConst graph) {
        this.graph = graph;
        this.nodes = new HashMap<>();
    }

    /**
     * Applies the Dijkstra algorithm on the graph.
     */
    public void applyDijkstraAlgorithm() {
        List<Node> tmpNodes = new ArrayList<>();
        Node tmp;
        boolean end = false;
        for (String name : this.nodes.keySet())
            tmpNodes.add(this.nodes.get(name));
        while (!tmpNodes.isEmpty() && !end) {
            tmp = findMin(tmpNodes);
            if (tmp == null)
                end = true;
            else {
                tmpNodes.remove(tmp);
                for (String succ : graph.getSucc(tmp.name))
                    if (tmpNodes.contains(nodes.get(succ)))
                        updateLength(tmp.name, succ);
            }
        }
    }

    /**
     * Finds the node with the minimum value in the list.
     * @param nodes list of nodes
     * @return the node with the minimum value
     */
    private static Node findMin(List<Node> nodes) {
        int distanceMin = Node.INFINITE;
        Node min = null;
        for (Node n : nodes)
            if (n.value < distanceMin) {
                distanceMin = n.value;
                min = n;
        }
        return min;
    }

    /**
     * Updates the length of the path between two nodes if the new path is shorter.
     * @param s1 first node
     * @param s2 second node
     */
    private void updateLength(String s1, String s2) {
        if (nodes.get(s2).value > nodes.get(s1).value + graph.getValuation(s1, s2)) {
            nodes.get(s2).value = nodes.get(s1).value + graph.getValuation(s1, s2);
            nodes.get(s2).previousNode = nodes.get(s1);
        }
    }

    /**
     * Adds the nodes of the graph to the nodes of the Dijkstra algorithm.
     */
    private void addSommetsToNodes() {
        for (String name : graph.getSommets())
            nodes.put(name, new Node(name));
    }

    /**
     * Initializes the nodes of the Dijkstra algorithm.
     * @param firstNode first node of the algorithm (starts with value 0)
     */
    private void initNodes(String firstNode) {
        for (String name : nodes.keySet()) {
            nodes.get(name).value = Node.INFINITE;
            nodes.get(name).previousNode = null;
        }
        if (nodes.containsKey(firstNode))
            nodes.get(firstNode).value = 0;
    }

    /**
     * Initializes the Dijkstra algorithm.
     * @param beginNode first node of the algorithm
     */
    private void initDijkstraAlgorithm(String beginNode) {
        if (!canApplyAlgorithm())
            throw new IllegalArgumentException("Le src.graphe n'est pas valide pour l'application de l'algorithme de Dijkstra.");
        nodes.clear();
        addSommetsToNodes();
        initNodes(beginNode);
    }

    /**
     * Dijkstra algorithm.
     * @param beginNode first node of the algorithm
     */
    public void dijsktraAlgorithm(String beginNode) {
        initDijkstraAlgorithm(beginNode);
        if (nodes.containsKey(beginNode))
            applyDijkstraAlgorithm();
    }

    /**
     * Returns the path between two nodes.
     * @param debut first node
     * @param fin second node
     * @return the path between the two nodes or null if there is no path
     */
    public ArrayList<String> chemin(String debut, String fin) {
        ArrayList<String> path = new ArrayList<>();
        dijsktraAlgorithm(debut);
        Node loopNode = nodes.get(fin);
        while (loopNode != null) {
            path.add(loopNode.name);
            loopNode = loopNode.previousNode;
        }
        Collections.reverse(path);
        if (!(path.get(0).equals(debut) && path.get(path.size() - 1).equals(fin)))
            return null; // INFINITE
        return path;
    }

    /**
     * Returns the distance between two nodes.
     * @param debut first node
     * @param fin second node
     * @return the distance between the two nodes or -1 if there is no path
     */
    public int distance(String debut, String fin) {
        dijsktraAlgorithm(debut);
        return nodes.get(fin).value;
    }

    /**
     * Tests if the graph is valid for the Dijkstra algorithm.
     * @return true if the graph is valid, false otherwise
     */
    public boolean canApplyAlgorithm() {
        for (Node node : nodes.values())
            for (String node2 : graph.getSucc(node.name))
                if (graph.contientArc(node.name, node2))
                    if (graph.getValuation(node.name, node2) < 0)
                        return false;
        return true;
    }

    /**
     * Subclass of Node for the Dijkstra algorithm.
     */
    private class Node {
        /** Infinite node, indicates that there is no path to that node yet. */
        public static final int INFINITE = Integer.MAX_VALUE;
        /** Name of the node. */
        public String name;
        /** Previous node in the path. */
        public Node previousNode;
        /** Value of the path to that node. */
        public int value;

        /**
         * Constructor of the Node class.
         * @param name name of the node
         */
        public Node(String name) {
            this.name = name;
            this.value = INFINITE;
            this.previousNode = null;
        }
    }
}
