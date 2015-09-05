// the code for exercise 2
// Bellman.java

/**
 * 
 * @author Yifei Pei a1611648
 *
 */

public class Bellman {
	
	private final static double INFINITY = 10000;
	private final static double NEGATIVEINFINITY = -10000;
	
	private Path [] shortestpath;
	private AdjListGraph graph;
	private Node source;
	
	// initialise
	public Bellman (AdjListGraph G) {
		graph = G;
		shortestpath = new Path [G.Nodelength()];
		source = null;
		//System.out.println(shortestpath.length);
	}
	
	// implement the algorithm
	public void BellmanFord (Node s) {
		//System.out.println(s.getNode());
		source = s;
		for (int i=1;i<=graph.Nodelength();i++) {
			if (graph.getNode(i) == source) {
				shortestpath[i-1].setNode(source);
				shortestpath[i-1].setPath(0.0);
			}else{
				shortestpath[i-1].setNode(graph.getNode(i));
				shortestpath[i-1].setPath(INFINITY);
			}
		}
		for (int j=0;j<graph.Edgelength();j++) {
			relax (graph.getEdge(j));
		}
		for (int k=0;k<graph.Edgelength();k++) {
			check (graph.getEdge(k));
		}
		
	}
	
	// do relax
	public void relax (Edge e) {
		Node u = e.getHeadnode();
		Node v = e.getTailnode();
		for (int i=0;i<shortestpath.length;i++) {
			for (int j=0;j<shortestpath.length;j++) {
				if (shortestpath[i].getNode() == u && shortestpath[j].getNode() == v) {
					if (shortestpath[i].getPath() + e.getWeight() < shortestpath[j].getPath()) {
						shortestpath[j].setPath(shortestpath[i].getPath() + e.getWeight());
						shortestpath[j].setPredecessor(u);
					}
				}
			}
		}
		
	}
	
	// do the infect to check negative-cycle
	public void check (Edge e) {
		Node u = e.getHeadnode();
		Node v = e.getTailnode();
		for (int i=0;i<shortestpath.length;i++) {
			for (int j=0;j<shortestpath.length;j++) {
				if (shortestpath[i].getNode() == u && shortestpath[j].getNode() == v) {
					if (shortestpath[i].getPath() + e.getWeight() < shortestpath[j].getPath()) {
						infect (v);
					}
				}
			}
		}
	}
	
	// do the infect to get rid of negative-cycle
	public void infect (Node v) {
		for (int i=0;i<shortestpath.length;i++) {
			if (shortestpath[i].getNode() == v) {
				if (shortestpath[i].getPath() > NEGATIVEINFINITY) {
					shortestpath[i].setPath(NEGATIVEINFINITY);
				}
			}
		}
		for (int j=0;j<graph.DiOutgoingEdge(v).size();j++) {
			infect (graph.DiOutgoingEdge(v).get(j).getTailnode());
		}
	}
	
	//print out the shortest path
	public void output () {
		double cost = 0.0;
		String output = "\n\t--";
		output += shortestpath[0].getNode().getNode() + " ";
        for(int i = 0; i < shortestpath.length; i++) {
        	if (shortestpath[i].getPredecessor() != null) {
        		output += " --> " + shortestpath[i].getNode().getNode();
        		cost += shortestpath[i].getPath();
        	}
            output += "\n\tthe cost for this path is: " + cost;            
        }
        System.out.println(output);
	}
}
