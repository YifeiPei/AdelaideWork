package artificialIntelligenceUnit;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import junit.framework.Assert;

import mapDataStructure.Map;
import mapDataStructure.RobotMap;

import org.junit.Before;
import org.junit.Test;

import controllerFSM.ControllerFSM;

/**
 * @author Matthew Nestor
 * @filename GraphTest.java
 * @package artificialIntelligenceUnit
 * @project HostSide
 * @date 03/11/2013
 */

public class GraphTest {
	private Graph graph;
	private Map map;

	@Before
	public void before(){
		map = new RobotMap();
		map.loadMap("map6.xml");
		graph = new Graph(map);
		graph.initialiseGraph();
	}

	@Test
	public void testCollisionCalculation() {
		graph.print();
		CollisionResult result = graph.testCollision(new Point(0, 0),
				new Point(2, 0),
				new Point(-1, 0),
				2.0);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(1, 0), result.firstCollision());
		

		result = graph.testCollision(new Point(0, 0),
				new Point(2, 0),
				new Point(3, 0),
				2.0);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(1, 0), result.firstCollision());

		result = graph.testCollision(new Point(0, 0),
				new Point(10, 0),
				new Point(5, 1),
				2.0);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(true, result.twoCollisions());
		Assert.assertEquals(new Point(3, 0), result.firstCollision());
		Assert.assertEquals(new Point(6, 0), result.secondCollision());

		result = graph.testCollision(new Point(0, 0),
				new Point(10, 0),
				new Point(5, 1),
				1.0);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(5, 0), result.firstCollision());

		result = graph.testCollision(new Point(10, 0),
				new Point(10, 10),
				new Point(10, 20),
				5.0);
		result.print();
		Assert.assertEquals(false, result.collision());
		Assert.assertEquals(false, result.twoCollisions());

		result = graph.testCollision(new Point(120, 20),
				new Point(120, 40),
				new Point(120, 80),
				27.0);
		result.print();
		Assert.assertEquals(false, result.collision());
		Assert.assertEquals(false, result.twoCollisions());

		result = graph.testCollision(new Point(120, 20),
				new Point(120, 0),
				new Point(120, 80),
				27.0);
		result.print();
		Assert.assertEquals(false, result.collision());
		Assert.assertEquals(false, result.twoCollisions());

		result = graph.testCollision(new Point(120, 40),
				new Point(120, 80),
				new Point(120, 80),
				27.0);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(120, 53), result.firstCollision());
		
		result = graph.testCollision(new Point(0, 0),
				new Point(0, 10),
				new Point(5, 5),
				5);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(0, 5), result.firstCollision());
		
		result = graph.testCollision(new Point(0, 0),
				new Point(0, 10),
				new Point(5, 5),
				100);
		result.print();
		Assert.assertEquals(false, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		
		result = graph.testCollision(new Point(5, 10),
				new Point(5, 1),
				new Point(5, 0),
				5);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(5, 5), result.firstCollision());
		
		result = graph.testCollision(new Point(5, 1),
				new Point(5, 10),
				new Point(5, 0),
				5);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(5, 5), result.firstCollision());
		
		result = graph.testCollision(new Point(5, 1),
				new Point(5, -10),
				new Point(5, 0),
				5);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(false, result.twoCollisions());
		Assert.assertEquals(new Point(5, -5), result.firstCollision());
		
		result = graph.testCollision(new Point(5, 10),
				new Point(5, -10),
				new Point(5, 0),
				5);
		result.print();
		Assert.assertEquals(true, result.collision());
		Assert.assertEquals(true, result.twoCollisions());
		Assert.assertEquals(new Point(5, 5), result.firstCollision());
		Assert.assertEquals(new Point(5, -5), result.secondCollision());
	}

	@Test
	public void testIsInBetween(){
		Assert.assertEquals(true, graph.isInBetween(new Point(120, 20), new Point(120, 40), new Point(120, 40)));
		Assert.assertEquals(true, graph.isInBetween(new Point(50, 20), new Point(120, 20), new Point(100, 20)));
		Assert.assertEquals(false, graph.isInBetween(new Point(120, 20), new Point(120, 40), new Point(120, 41)));
		Assert.assertEquals(false, graph.isInBetween(new Point(120, 20), new Point(120, 40), new Point(100, 38)));
		Assert.assertEquals(false, graph.isInBetween(new Point(120, 20), new Point(150, 20), new Point(151, 20)));
		Assert.assertEquals(false, graph.isInBetween(new Point(120, 20), new Point(150, 20), new Point(100, 21)));
	}
	
	@Test
	public void testAStarSearch(){
		Vertex start = graph.getInitialVertex();
		Vertex goal = graph.goals.peek();
		ArrayList<Vertex> solution = graph.AStarSearch(start, goal);
		Assert.assertEquals(solution, graph.getSolution());
		Assert.assertEquals(new Point(29, 28), solution.remove(0).getCoordinate());
		Assert.assertEquals(new Point(29, 21), solution.remove(0).getCoordinate());
		Assert.assertEquals(new Point(29, 1), solution.remove(0).getCoordinate());
	}
	
	@Test
	public void testUpdateGraph(){
		Deque<Action> actionList = graph.updateGraph(); //calls AStarSearch and returns AStarSearch array
		Action nextAction = actionList.remove();
		Assert.assertEquals("NORTH", nextAction.getAction());
		Assert.assertEquals(20, nextAction.getDistance());
		nextAction = actionList.remove();
		Assert.assertEquals("NORTH", nextAction.getAction());
		Assert.assertEquals(7, nextAction.getDistance());
		graph.print();
	}
	
	@Test
	public void testMisc() throws InterruptedException{
		Vertex start = graph.getInitialVertex();
		LinkedList<Edge> outEdges = graph.outEdgesOf(start);
		for(Edge e: outEdges){
			Vertex v = e.toVertex();
			Assert.assertEquals(e, graph.getEdge(start, v));
			Assert.assertEquals(true, graph.containsEdge(e));
		}
		Vertex fakeVertex = new Vertex(21, new Point(1, 1));
		Assert.assertEquals(false, graph.containsEdge(start, fakeVertex));
		Assert.assertEquals(null, graph.getEdge(start, fakeVertex));
		Vertex nextVertex = graph.transition(start, "NORTH");
		graph.removeEdge(start, nextVertex);
		graph.removeEdge(nextVertex, start);
		Assert.assertEquals(false, graph.containsEdge(start, nextVertex));
		Vertex goal = graph.getVertex(new Point(55, 21));
		Assert.assertEquals(true, goal.isGoal());
		goal = graph.getVertex(new Point(-1, -2));
		Assert.assertEquals(null, goal);
		Vertex v2 = graph.transition(nextVertex, "SOUTH");
		Assert.assertEquals(null, v2);
		int tempInt = graph.stepCost(nextVertex, "SOUTH");
		Assert.assertEquals(-1, tempInt);
	}

}
