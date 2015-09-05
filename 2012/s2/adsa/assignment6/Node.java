// Node.java
// added some new methods regarding assignment5

/**
 * 
 * @author Yifei Pei
 * a1611648
 *
 */

public class Node {
	
	private String node;
	private boolean isVisited;
	private double distance;
	
	public Node (String n) {
		this.node = n;
		this.isVisited = false;
		this.distance = 0;
	}
	
	public Node (String n, double m) {
		this.node = n;
		this.isVisited = false;
		this.distance = m;
	}
	
	public Node () {
		this.node = "";
	}

	public String getNode () {
		return node;
	}
	
	public void setNode (String n) {
		this.node = n;
	}
	
	public void printNode () {
		System.out.println(node + " ");
	}
	
	public boolean isVisited () {
		return isVisited;
	}
	
	public void visit () {
		this.isVisited = true;
	}
	
	public void setDistance (double n) {
		this.distance = n;
	}
	
	public double getDistance () {
		return distance;
	}
}
