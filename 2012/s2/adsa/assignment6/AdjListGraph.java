//AdjListGraph.java
//added some new methods regarding assignment5

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * 
 * @author Yifei Pei
 * a1611648
 *
 */

public class AdjListGraph {
		
	private Node [] nodes;
	private LinkedList <Edge> edges;

	/**
	 * constructor
	 * @param n the number of nodes
	 */
	
	public AdjListGraph (int n) {
		this.nodes = new Node [n];
		for (int i=1;i<=n;i++) {
			nodes[i-1] = new Node ("" + i);
		}
		this.edges = new LinkedList <Edge> ();
	}
	
	/**
	 * add edge
	 * @param e, the edge
	 */
	
	public void addEdge (Edge e) {
		edges.add(e);
	}
	
	/**
	 * add edge with details
	 * @param u the head node
	 * @param v the tail node
	 * @param weight the weight of the edge
	 */
	
	public void addEdge (int u, int v, double weight) {
		edges.add(new Edge (weight, nodes[u-1], nodes[v-1]));
	}
	
	/**
	 * remove edge
	 * @param e the edge
	 */
	
	public void removeEdge (Edge e) {
		if (this.checkEdge(e) == true)
		    edges.remove (e);
	}
	
	/**
	 * remove the edge with details
	 * @param u the head node
	 * @param v the tail node
	 * @param weight the weight of the edge
	 */
	
	public void removeEdge (int u, int v, double weight) {
		for (int i=0;i<edges.size();i++) {
			if ((edges.get(i).getHeadnode() == nodes[u-1]) && (edges.get(i).getTailnode() == nodes[v-1]) && (edges.get(i).getWeight() == weight)) {
				edges.remove(i);
			}
		}
	}
	
	/**
	 * get outgoing edges of one particular node
	 * @param s the given node
	 * @return LinkedList of the edges outgoing from s
	 */
	
	public LinkedList<Edge> GetOutgoingEdge (Node s) {
		LinkedList <Edge> GetOutgoingEdge = new LinkedList <Edge> ();
		for (int i=0;i<this.nodes.length; i++) {
			if(this.nodes[i] == s) {
				for (int j=0;j<edges.size();j++) {
					if ((edges.get(j).getHeadnode() == nodes[i]) || (edges.get(j).getTailnode() == nodes[i])) {
						GetOutgoingEdge.add(edges.get(j));
					}
				}
            }
		}
		return GetOutgoingEdge;
	}
	
	public LinkedList<Edge> DiOutgoingEdge (Node s) {
		LinkedList <Edge> DiOutgoingEdge = new LinkedList <Edge> ();
		for (int i=0;i<this.nodes.length; i++) {
			if(this.nodes[i] == s) {
				for (int j=0;j<edges.size();j++) {
					if ((edges.get(j).getHeadnode() == nodes[i])) {
						DiOutgoingEdge.add(edges.get(j));
					}
				}
            }
		}
		return DiOutgoingEdge;
	}
	
	/**
	 * To check the existence of an edge
	 * @param e the edge
	 * @return boolean status of the edge
	 */
	
	public boolean checkEdge (Edge e) {
		for (int i=0;i<edges.size();i++) {
			if (edges.get(i) == e) {
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * to check the existence of an edge with details
	 * @param u the head node
	 * @param v the tail node
	 * @param weight the weight of the edge
	 * @return boolean status
	 */
	
	public boolean checkEdge (int u, int v, double weight) {
		for (int i=0;i<edges.size();i++) {
			if ((edges.get(i).getHeadnode() == nodes[u-1]) && (edges.get(i).getTailnode() == nodes[v-1]) && (edges.get(i).getWeight() == weight)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * To print the graph in a suitable format
	 */
	
	public void output () {
		String output = "";
        output += "Total nodes: " + this.nodes.length;
        for(int i = 0; i < this.nodes.length; i++) {
            output += "\n\tGraph Node: " + this.nodes[i].getNode();
            for(int j = 0; j < edges.size(); j++) {
            	if (edges.get(j).getHeadnode() == nodes[i]) {
            		output += "\n\t\t -- " + edges.get(j).ToString();
            	}
            }
        }
        System.out.println(output);
	}
	
	/**
	 * compute the distances from the given node s using breadth-first-search
	 * @param s the given node
	 */

	
	public void computeDistances (Node s) {
		ArrayList <Node> Next = new ArrayList <Node> ();
		ArrayList <Node> Current = new ArrayList <Node> ();
		Node parent = new Node ();
		int distance = 0;
		for (int i=0;i<this.nodes.length; i++) {
			if(this.nodes[i] == s) {
				parent = nodes[i];
				while (Current.size() < nodes.length) {
				for (int j=0;j<this.GetOutgoingEdge(parent).size();j++) {
				    if ((this.GetOutgoingEdge(parent).get(j).getHeadnode() == parent) && (this.GetOutgoingEdge(parent).get(j).getTailnode() != null)) {
				    	Next.add(this.GetOutgoingEdge(parent).get(j).getTailnode());
				    }
				    if ((this.GetOutgoingEdge(parent).get(j).getTailnode() == parent) && (this.GetOutgoingEdge(parent).get(j).getHeadnode() != null)) {
				    	Next.add(this.GetOutgoingEdge(parent).get(j).getHeadnode());
				    }
				}
				Current.addAll(Next);
				parent = Next.get(0);
				distance++;
				Next.clear();
				}
			}
		}
		System.out.println("The distance from Node " + s.getNode() + " is " + distance);
	}
	
	
	
	public Node getNode (int n) {
		return nodes[n-1];
	}
	
	public Edge getEdge (int u, int v) {
		Edge e = null;
		for (int i=0;i<edges.size();i++) {
			if ((edges.get(i).getHeadnode() == nodes[u-1]) && (edges.get(i).getTailnode() == nodes[v-1])) {
				e = edges.get(i);
			}
		}
		return e;
	}
	
	public Edge getEdge (int i) {
		return edges.get(i);
	}
	
	public int Nodelength () {
		return nodes.length;
	}
	
	public int Edgelength () {
		return edges.size();
	}
}
