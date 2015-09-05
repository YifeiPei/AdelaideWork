// Driver.java
// the testing class

/**
 * 
 * @author Yifei Pei
 * a1611648
 *
 */

public class Driver {
	public static void main(String[] args) {
		 AdjListGraph graph1 = new AdjListGraph(8);
		 graph1.addEdge(1, 2, 5.0);
		 graph1.addEdge(1, 4, 4.0);
		 graph1.addEdge(2, 4, 3.0);
		 graph1.addEdge(2, 3, 4.0);
		 graph1.addEdge(4, 3, 3.0);
		 graph1.addEdge(4, 5, 8.0);
		 graph1.addEdge(3, 6, 8.0);
		 graph1.addEdge(5, 6, 2.0);
		 graph1.addEdge(5, 8, 5.0);
		 graph1.addEdge(7, 6, 6.0);
		 graph1.addEdge(8, 7, 1.0);
		 System.out.println("the first graph: ");
		 graph1.output();
		 Bellman B1 = new Bellman (graph1);
		 B1.BellmanFord(graph1.getNode(1));
		 System.out.println("the shortestpath for the first graph: ");
		 B1.output();
		 AdjListGraph graph2 = new AdjListGraph(5);
		 graph2.addEdge(1, 2, 4.0);
		 graph2.addEdge(1, 3, 6.0);
		 graph2.addEdge(1, 4, 0.0);
		 graph2.addEdge(4, 3, 5.0);
		 graph2.addEdge(4, 5, 1.0);
		 graph2.addEdge(2, 4, 3.0);
		 graph2.addEdge(5, 2, 2.0);
		 System.out.println("the second graph: ");
		 graph2.output();
		 Bellman B2 = new Bellman (graph2);
		 B2.BellmanFord(graph2.getNode(1));
		 System.out.println("the shortestpath for the second graph: ");
		 B2.output();
		 AdjListGraph graph3 = new AdjListGraph(8);
		 graph3.addEdge(1, 2, -1.0);
		 graph3.addEdge(1, 5, 1.0);
		 graph3.addEdge(2, 3, -2.0);
		 graph3.addEdge(2, 6, 2.0);
		 graph3.addEdge(3, 4, -3.0);
		 graph3.addEdge(3, 7, 4.0);
		 graph3.addEdge(4, 1, -4.0);
		 graph3.addEdge(4, 8, 7.0);
		 System.out.println("the third graph: ");
		 graph3.output();
		 Bellman B3 = new Bellman (graph3);
		 B3.BellmanFord(graph3.getNode(1));
		 System.out.println("the shortestpath for the third graph: ");
		 B3.output();		 
	}
}
