package pcc;

import graphe.IGrapheConst;

import java.util.*;

public class PCCDijkstra {
    private final Map<String, Node> nodes;
    IGrapheConst graph;

    public PCCDijkstra(IGrapheConst graph) {
        this.graph = graph;
        this.nodes = new HashMap<>();
    }

    public void applyDijkstraAlgorithm() {
        List<Node> tmpNodes = new ArrayList<>();
        Node tmp;
        boolean end = false;
        for (String name : this.nodes.keySet())
            tmpNodes.add(this.nodes.get(name));
        while (tmpNodes.size() > 0 && !end) {
            tmp = findMin(tmpNodes);
            if (tmp == null)
                end = true;
            else {
                tmpNodes.remove(tmp);
                for (String succ : graph.getSucc(tmp.name))
                    updateLength(tmp.name, succ);
            }
        }
    }

    private Node findMin(List<Node> nodes) {
        int distanceMin = Node.INFINITE;
        Node min = null;
        for (Node n : nodes)
            if (n.value < distanceMin) {
                distanceMin = n.value;
                min = n;
        }
        return min;
    }

    private void updateLength(String s1, String s2) {
        if (nodes.get(s2).value > nodes.get(s1).value + graph.getValuation(s1, s2)) {
            nodes.get(s2).value = nodes.get(s1).value + graph.getValuation(s1, s2);
            nodes.get(s2).previousNode = nodes.get(s1);
        }
    }

    private void addSommetsToNodes() {
        for (String name : graph.getSommets())
            nodes.put(name, new Node(name));
    }

    private void initNodes(Node firstNode) {
        for (String name : nodes.keySet()) {
            nodes.get(name).value = Node.INFINITE;
            nodes.get(name).previousNode = null;
        }
        firstNode.value = 0;
    }

    private void initDijkstraAlgorithm(String beginNode) {
        if (!canApplyAlgorithm())
            throw new IllegalArgumentException("Le graphe n'est pas valide pour l'application de l'algorithme de Dijkstra.");
        nodes.clear();
        addSommetsToNodes();
        initNodes(nodes.get(beginNode));
    }

    public void dijsktraAlgorithm(String beginNode) {
        initDijkstraAlgorithm(beginNode);
        applyDijkstraAlgorithm();
    }

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

    public boolean canApplyAlgorithm() {
        for (Node node : nodes.values())
            for (String node2 : graph.getSucc(node.name))
                if (graph.contientArc(node.name, node2))
                    if (graph.getValuation(node.name, node2) < 0)
                        return false;
        return true;
    }

    private class Node {
        public static final int INFINITE = Integer.MAX_VALUE;
        public String name;
        public Node previousNode;
        public int value;

        public Node(String name) {
            this.name = name;
            this.value = INFINITE;
            this.previousNode = null;
        }
    }
}
