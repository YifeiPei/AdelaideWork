// Edge.java
// added some new methods regarding assignment5

/**
 * 
 * @author Yifei Pei
 * a1611648
 *
 */

public class Edge {
	
	private double weight;
	private Node headnode, tailnode;
	
	public Edge (double weight, Node u, Node v) {
		this.weight = weight;
		headnode = u;
		tailnode = v;
	}
	
	public void setWeight (double weight) {
		this.weight = weight;
	}
	
	public double getWeight () {
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
