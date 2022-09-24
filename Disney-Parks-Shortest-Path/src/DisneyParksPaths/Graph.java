package DisneyParksPaths;

import java.util.*;

/*
Graph is a representation of a labeled directed multigraph. Each node in the graph has a label
and each edge has a label as well. Nodes in the Graph are stored in a private HashMap called "nodes".
In the map, keys are strings which represent node labels. Values are sets of edges connected to the node.
Each edge is an Edge object which is represented by a parent node, a child node, and an edge label. There are no duplicate edges
or nodes in the graph
*/
public class Graph<N extends Comparable<N>,C extends Comparable<C>> {
	
	static final boolean docheckRep = false;  // variable that turns checkRep() on and off
	HashMap<N, HashSet<Edge<N, C>>> nodes;
	
	// Abstraction Function: 
	// A Graph is a directed labeled multi graph. It's nodes are represented as the set of keys from the HashMap nodes.
	// Each edge is represented by an Edge object in the set edges.
 
    // Representation invariant:
	// Edge and node values can't be null
	
	/**
     * @param none 
     * @requires none
     * @effects Constructs a new empty directed labeled graph with no nodes or edges
     */
  	public Graph() { 
  		nodes = new HashMap<N, HashSet<Edge<N, C>>>();
  		checkRep();
  	}
  	
	 /**
	 * Checks that the representation invariant holds.
	 * A graph must have nodes if it has edges.
	 */
    // Throws a RuntimeException if the representation invariant is violated.
	private void checkRep() throws RuntimeException {
		if (docheckRep) {
			for (N key : nodes.keySet()) {
			    if (nodes.get(key) == null)
			    	throw new RuntimeException("Node is null.");
			}
		}
		else
			return;
	} 
  
 	/**
     * @param node : a string that represents a node that will be added to the graph
     * @requires none
     * @effects Graph.nodes : adds a new node to the list
     */
  	public void addNode(N node) {
  		// if the node has a label and is not in the graph, we will add it
		if (!nodes.containsKey(node)) {
			nodes.put(node, new HashSet<Edge<N,C>>());
			checkRep();
		}  		
  	}
	
	/**
     * @param parent : a string that represents the 1st node in the edge
     * @param child : a string that represents the 2nd node in the edge
     * @param label : a label for the edge
     * @requires parent != null
     * @requires child != null
     * @requires label != null
     * @effects Graph.nodes : creates an edge object and adds it to the edge list
     * @ throws a runtime exception if any parameters are null
     */
  	public void addEdge(N parent, N child, C label) {
  		// check if both nodes in the graph
		if (nodes.containsKey(parent) && nodes.containsKey(child)) {
  			Edge<N,C> e = new Edge<N,C>(parent,child,label);
  			Set<Edge<N,C>> list = nodes.get(parent);
  			list.add(e);
  			checkRep();
		}
  	}
  	
	/**
     * @param none
     * @requires none
     * @effects none
     * @returns a map of all the nodes in the graph 
     */
	public HashMap<N,HashSet<Edge<N,C>>> getNodes() {
		return new HashMap<N, HashSet<Edge<N,C>>>(nodes);

  	}
  	
  	/**
     * @param node : a string that represents a node
     * @requires none
     * @effects none
     * @returns a boolean indicating whether or not the node is in the graph
     */
  	public boolean isNode(N node) {
    	return nodes.containsKey(node);
  	}
  	
  	/**
  	 * @param node1: first node making up the edge
  	 * @param node2: second node making up the edge
     * @requires none
     * @effects none
     * @returns a boolean indicating whether or not the edge is in the graph
     */
  	public boolean isEdge(N nodeone,N nodetwo) {
  		Set<Edge<N,C>> edges = nodes.get(nodeone); 
  		for (Iterator<Edge<N,C>> it = edges.iterator(); it.hasNext(); ) {
  	        Edge<N,C> e = it.next();
  	        if (e.getChild().equals(nodetwo))
  	            return true;
  	    }
  		return false;
  	}
  	
  	/**
     * @param node1 : parent node for an edge
     * @param node2 : child node for an edge
     * @param label : label for the edge we are searching for
     * @requires none
     * @effects none
     * @returns a boolean indicating whether or not the edge is in the graph
     */
  	public boolean isEdge(N nodeone,N nodetwo,C label) {
  		Set<Edge<N,C>> edges = nodes.get(nodeone);   	
  		if (edges != null){
	  		for (Edge<N,C> e : edges) {
	  	        if (e.getParent().equals(nodeone) && e.getChild().equals(nodetwo) && e.getLabel().equals(label))
	  	            return true;
	  	    }
  		}
  		return false;
  	}
}