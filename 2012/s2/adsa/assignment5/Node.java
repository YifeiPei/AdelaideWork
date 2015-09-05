// Node.java

/**
 * 
 * @author Yifei Pei
 * a1611648
 *
 */

public class Node {
	
	private String node;
	
	public Node (String n) {
		this.node = n;
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
}
