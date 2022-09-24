package DisneyParksPaths;


/** Edge is an immutable object that represents a connection 
between two nodes in a graph. Each edge will have a label 
value to represent it. It can hold any node and label type. 
*/

public class Edge <N extends Comparable<N>,C extends Comparable<C>> implements java.lang.Comparable<Edge<N,C>>{ 

	private N first;
	private N child;
	private C label;
	
	// Abstraction Function:
    // An Edge, e, is a connection between two nodes, parent and child, with the label of the edge being 
	// a string, l.

    // Representation invariant
	// parent child and label never null

	/**  
	@param node_1 : The parent node in the new Edge.
    @param node_2 : The child node in the new Edge.
    @param label : The label of the new Edge
    @effects Constructs a new Edge made up of two nodes, parent and child, with distance d.
	*/
	public Edge(N nodeone, N nodetwo,C tag) {
		first = nodeone;
		child = nodetwo;
		label = tag;
		checkRep();
	}
	
	/** 
	@param none
	@effects none
    @return returns the parent node for the Edge.
	*/
	public N getParent() {
		return first;
	}
	
	/** 
	@param none
    @effects none
    @return returns the child node for the edge
	*/
	public N getChild() {
		return child;
	}
	
	/** 
	@param none
    @effects none
    @return returns the label of the edge
	*/
	public C getLabel() {
		return label;
	}
	
	// Makes sure the representation invariant holds
	public void checkRep() {
		if (first == null) {
			throw new RuntimeException("Null First Node");
		}
		else if (child == null) {
			throw new RuntimeException("Null Child Node");
		}
		else if (label == null) {
			throw new RuntimeException("Null Label");
		}
	}
	
	public int compare(N eone,N etwo) {
	    return eone.compareTo(etwo);
	}
	
	public int compareTo(C eone,C etwo) {
	    return eone.compareTo(etwo);
	}
	
	/** 
	@param compareEdge: the edge we are comparing our current  edge to
    @effects none
    @return if the edge is less than or greater than the compared edge
	*/
//	 comparator for Edges
	public int compareTo(Edge<N,C> compareEdge) { 
		int compare = compare(this.getChild(), compareEdge.getChild());
		if (compare == 0) {
			return compareTo(this.getLabel(), compareEdge.getLabel());
		}
		// ascending order
		return compare; 
	}	
	
	
}