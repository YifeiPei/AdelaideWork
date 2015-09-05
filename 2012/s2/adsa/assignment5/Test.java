// Test.java

/**
 * 
 * @author Yifei Pei
 * a1611648
 *
 */

public class Test {
	public static void main(String[] args) {
		 AdjListGraph graph = new AdjListGraph(5);
		 graph.addEdge(1, 2, 1);
		 graph.addEdge(1, 4, 2);
		 graph.addEdge(2, 4, 3);
		 graph.addEdge(2, 3, 4);
		 graph.addEdge(4, 5, 5);
		 graph.addEdge(5, 3, 6);
		 graph.addEdge(5, 2, 7);
		 System.out.println("the new graph: ");
		 graph.output();
		 System.out.println("the graph has removed one edge: ");
		 graph.removeEdge(5, 3, 6);
		 graph.output();
		 graph.computeDistances(graph.getNode(1));
	}

}
