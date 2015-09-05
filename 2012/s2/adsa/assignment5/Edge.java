// Edge.java

/**
 * 
 * @author Yifei Pei
 * a1611648
 *
 */

public class Edge {
	
	private int weight;
	private Node headnode, tailnode;
	
	public Edge (int weight, Node u, Node v) {
		this.weight = weight;
		headnode = u;
		tailnode = v;
	}
	
	public void setWeight (int weight) {
		this.weight = weight;
	}
	
	public int getWeight () {
		return weight;
	}
	
	public void setHeadnode (Node u) {
		headnode = u;
	}
	
	public Node getHeadnode () {
		return headnode;
	}
	
	public void setTailnode (Node v) {
		tailnode = v;
	}
	
	public Node getTailnode () {
		return tailnode;
	}
	
	public String ToString () {
		String edge = "";
		String Tailnode = "-";
		if (this.tailnode != null)
			Tailnode = this.tailnode.getNode();
		edge = this.headnode.getNode() + " --> " + Tailnode + " Weight: " + this.weight;
		return edge;
	}
}
