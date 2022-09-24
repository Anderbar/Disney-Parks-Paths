package DisneyParksPaths;

import java.util.*;

/**
 * This class is a wrapper class for the Graph class. It will help us test the Graph 
 * class by reducing the number of component.
 * @param <S>
 */
public class GraphWrapper<N extends Comparable<N>,C extends Comparable <C>> {
	private Graph<N,C> G;
	
	/**
     * @param none
     * @requires none
     * @effects none
     * @returns a GraphWrapper object with private Graph g  
     */
	public GraphWrapper() {
		G = new Graph<N,C>();
	}
	
	/**
     * @param node : string representing a node
     * @requires none
     * @effects adds a node to graph G
     * @returns none 
     */
	public void addNode(N node){
		G.addNode(node);
	}
	
	/**
     * @param parent : string representing a parent node
     * @param child : string representing a child node
     * @param label : string representing an edge label
     * @requires none
     * @effects adds an edge to graph G
     * @returns none
     */
	public void addEdge(N parent,N child,C label) {
		G.addEdge(parent,child,label);
	}
	
	/**
     * @param none
     * @requires none
     * @effects none
     * @returns an iterator to a sorted list of nodes in Graph G
     */
	public Iterator<N> listNodes() {
		// get the nodes from the graph, sort all the keys and return an iterator to the list
		List<N> nodes = new ArrayList<N>(G.getNodes().keySet());
		Iterator<N> itr = nodes.iterator();
		return itr;
	}
	
	/**
     * @param node : a String representing a node
     * @requires none
     * @effects none
     * @returns an iterator to a sorted list of a node's children 
     */
	public Iterator<String> listChildren(N node) {
		// get all the edges in the graph
		Set<Edge<N,C>> edges = G.getNodes().get(node);
		List<String> children = new ArrayList<String>();
		if (edges != null){
			for (Edge<N,C> e: edges) {
				String child = e.getChild().toString();
				String label = e.getLabel().toString();
	  			String rep = child+"("+label+")";
	  			children.add(rep);
	    	}
		}
		// sort
		Collections.sort(children);
  		Iterator<String> itr = children.iterator();
		return itr;
	}
}