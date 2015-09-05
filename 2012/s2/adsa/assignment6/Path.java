//Path.java
//create the data structure to hold shortest path

/**
 * 
 * @author Yifei Pei a1611648
 *
 */

public class Path {
	private Node toNode;
	private double distance;
	private Node predecessor;
	
	Path () {
		toNode = null;
		distance = 0;
		predecessor = null;
	}
	
	Path (Node u, double d) {
		toNode = u;
		distance = d;
		predecessor = null;
	}
	
	public void setNode (Node n) {
		this.toNode = n;
	}
	
	public Node getNode () {
		return toNode;
	}
	
	public String nodeName () {
		return toNode.getNode();
	}
	
	public double getPath () {
		return distance;
	}
	
	public void setPath (double n) {
		this.distance = n;
	}
	
	public void setPredecessor (Node n) {
		this.predecessor = n;
	}
	
	public Node getPredecessor () {
		return this.predecessor;
	}
	
}
