package graphe;

/**
 * Interface for graphs.
 */
public interface IGraphe extends IGrapheConst {
	/**
	 * Adds a node to the graph.
	 * @param noeud the node to add
	 */
	void ajouterSommet(String noeud);

	/**
	 * Adds a directed edge to the graph.
	 * @param source the source node
	 * @param destination the destination node
	 * @param valeur the value of the edge
	 */
	void ajouterArc(String source, String destination, Integer valeur);

	/**
	 * Removes a node from the graph.
	 * @param noeud the node to remove
	 */
	void oterSommet(String noeud);

	/**
	 * Removes a directed edge from the graph.
	 * @param source the source node
	 * @param destination the destination node
	 */
	void oterArc(String source, String destination);

	/**
	 * Builds a graph from a string.
	 * @param str the string to parse in format "A-B(5), A-C(10), B-C(3), C-D(8), E:"
	 */
	default void peupler(String str) {
	    assert this.getSommets().isEmpty();
	    String[] arcs = str.split(",\\s*");
	    for (String arc : arcs) {
	        String[] elements = arc.trim().split("-");

	        // extrait le noeud source et ote ":" si nécessaire dans le nom
	        String src = elements[0].replaceAll(":", "");
	        ajouterSommet(src);

	        if (elements.length > 1 && !elements[1].isEmpty()) {
	            String[] targets = elements[1].split(",\\s*");
	            for (String target : targets) {
	                String dest = target.substring(0, target.indexOf('('));
	                int val = Integer.parseInt(target
	                		.substring(target.indexOf('(') + 1,
	                				   target.indexOf(')')));
	                ajouterArc(src, dest, val);
	            }
	        }
	    }
	}	
}