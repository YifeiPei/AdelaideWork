package mapDataStructure;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bowen Tao
 * @filename RobotMapTest.java
 */
@SuppressWarnings("deprecation")

public class RobotMapTest {

	RobotMap rmap;
	
	@Before
	public void before(){
		rmap = new RobotMap();
		rmap.loadMap("src/map4.xml");
	}
	
	@Test
	public void boundaryTest(){
		double height=rmap.getHeight();
		double width=rmap.getWidth();
		Assert.assertEquals(180,height,0.00001);
		Assert.assertEquals(240, width, 0.00001);
	}
	
	@Test
	public void disaterTest() {	
		int disaterSize =rmap.getDisasterZones().size();
		assertEquals(2, disaterSize);
		
		double x =rmap.getDisasterZones().get(0).getLocation().getX();
		double y =rmap.getDisasterZones().get(0).getLocation().getY();
		Assert.assertEquals(70, x, 0.00001);
		Assert.assertEquals(40, y, 0.00001);
		
		double x1 =rmap.getDisasterZones().get(1).getLocation().getX();
		double y1 =rmap.getDisasterZones().get(1).getLocation().getY();
		Assert.assertEquals(120, x1, 0.00001);
		Assert.assertEquals(80, y1, 0.00001);
		
	}
	
	@Test
	public void intersectionTest() {
		int intersectionSize =rmap.getIntersections().size();
		assertEquals(8, intersectionSize);
		
		double x =rmap.getIntersections().get(0).getLocation().getX();
		double y =rmap.getIntersections().get(0).getLocation().getY();
		Assert.assertEquals(120, x, 0.00001);
		Assert.assertEquals(40, y, 0.00001);
		
		double x1 =rmap.getIntersections().get(1).getLocation().getX();
		double y1 =rmap.getIntersections().get(1).getLocation().getY();
		Assert.assertEquals(100, x1, 0.00001);
		Assert.assertEquals(40, y1, 0.00001);
		
		double x2 =rmap.getIntersections().get(2).getLocation().getX();
		double y2 =rmap.getIntersections().get(2).getLocation().getY();
		Assert.assertEquals(100, x2, 0.00001);
		Assert.assertEquals(80, y2, 0.00001);
		
		double x3 =rmap.getIntersections().get(3).getLocation().getX();
		double y3 =rmap.getIntersections().get(3).getLocation().getY();
		Assert.assertEquals(120, x3, 0.00001);
		Assert.assertEquals(80, y3, 0.00001);
		
		double x4 =rmap.getIntersections().get(4).getLocation().getX();
		double y4 =rmap.getIntersections().get(4).getLocation().getY();
		Assert.assertEquals(160, x4, 0.00001);
		Assert.assertEquals(40, y4, 0.00001);
		
		double x5 =rmap.getIntersections().get(5).getLocation().getX();
		double y5 =rmap.getIntersections().get(5).getLocation().getY();
		Assert.assertEquals(160, x5, 0.00001);
		Assert.assertEquals(80, y5, 0.00001);
		
		double x6 =rmap.getIntersections().get(6).getLocation().getX();
		double y6 =rmap.getIntersections().get(6).getLocation().getY();
		Assert.assertEquals(100, x6, 0.00001);
		Assert.assertEquals(120, y6, 0.00001);
		
		double x7 =rmap.getIntersections().get(7).getLocation().getX();
		double y7 =rmap.getIntersections().get(7).getLocation().getY();
		Assert.assertEquals(160, x7, 0.00001);
		Assert.assertEquals(120, y7, 0.00001);
		
	}
	
	@Test
	public void obstaclesTest() {
		int obstacleSize =rmap.getObstacles().size();
		assertEquals(2, obstacleSize);
		
		double x =rmap.getObstacles().get(0).getLocation().getX();
		double y =rmap.getObstacles().get(0).getLocation().getY();
		Assert.assertEquals(200, x, 0.00001);
		Assert.assertEquals(40, y, 0.00001);
		
		double x1 =rmap.getObstacles().get(1).getLocation().getX();
		double y1 =rmap.getObstacles().get(1).getLocation().getY();
		Assert.assertEquals(100, x1, 0.00001);
		Assert.assertEquals(105, y1, 0.00001);
		
	}
	
	@Test
	public void roadsTest() {
		int roadSize =rmap.getRoads().size();
		assertEquals(6, roadSize);
		
		double startX =rmap.getRoads().get(0).getStart().getX();
		double startY =rmap.getRoads().get(0).getStart().getY();
		Assert.assertEquals(120, startX, 0.00001);
		Assert.assertEquals(0, startY, 0.00001);
		double endX =rmap.getRoads().get(0).getEnd().getX();
		double endY =rmap.getRoads().get(0).getEnd().getY();
		Assert.assertEquals(120, endX, 0.00001);
		Assert.assertEquals(80, endY, 0.00001);
		
		double startX1 =rmap.getRoads().get(1).getStart().getX();
		double startY1 =rmap.getRoads().get(1).getStart().getY();
		Assert.assertEquals(80, startX1, 0.00001);
		Assert.assertEquals(40, startY1, 0.00001);
		double endX1 =rmap.getRoads().get(1).getEnd().getX();
		double endY1 =rmap.getRoads().get(1).getEnd().getY();
		Assert.assertEquals(200, endX1, 0.00001);
		Assert.assertEquals(40, endY1, 0.00001);
		
		double startX2 =rmap.getRoads().get(2).getStart().getX();
		double startY2 =rmap.getRoads().get(2).getStart().getY();
		Assert.assertEquals(100, startX2, 0.00001);
		Assert.assertEquals(40, startY2, 0.00001);
		double endX2 =rmap.getRoads().get(2).getEnd().getX();
		double endY2 =rmap.getRoads().get(2).getEnd().getY();
		Assert.assertEquals(100, endX2, 0.00001);
		Assert.assertEquals(120, endY2, 0.00001);
		
		double startX3 =rmap.getRoads().get(3).getStart().getX();
		double startY3 =rmap.getRoads().get(3).getStart().getY();
		Assert.assertEquals(160, startX3, 0.00001);
		Assert.assertEquals(40, startY3, 0.00001);
		double endX3 =rmap.getRoads().get(3).getEnd().getX();
		double endY3 =rmap.getRoads().get(3).getEnd().getY();
		Assert.assertEquals(160, endX3, 0.00001);
		Assert.assertEquals(120, endY3, 0.00001);
		
		double startX4 =rmap.getRoads().get(4).getStart().getX();
		double startY4 =rmap.getRoads().get(4).getStart().getY();
		Assert.assertEquals(100, startX4, 0.00001);
		Assert.assertEquals(80, startY4, 0.00001);
		double endX4 =rmap.getRoads().get(4).getEnd().getX();
		double endY4 =rmap.getRoads().get(4).getEnd().getY();
		Assert.assertEquals(160, endX4, 0.00001);
		Assert.assertEquals(80, endY4, 0.00001);
		
		double startX5 =rmap.getRoads().get(5).getStart().getX();
		double startY5 =rmap.getRoads().get(5).getStart().getY();
		Assert.assertEquals(100, startX5, 0.00001);
		Assert.assertEquals(120, startY5, 0.00001);
		double endX5 =rmap.getRoads().get(5).getEnd().getX();
		double endY5 =rmap.getRoads().get(5).getEnd().getY();
		Assert.assertEquals(160, endX5, 0.00001);
		Assert.assertEquals(120, endY5, 0.00001);
		
	}
	
	@Test
	public void unexploredZonesTest() {
		double x =rmap.getUnexploredZones().get(0).getLocation().getX();
		double y =rmap.getUnexploredZones().get(0).getLocation().getY();
		Assert.assertEquals(0, x, 0.00001);
		Assert.assertEquals(41, y, 0.00001);

		
	}
	
	@Test
	public void closureTest() {
		int closureSize = 0;
		ArrayList <Road> roads = new ArrayList<Road>();
		roads = rmap.getRoads();
		for (Road r: roads){
			if (r.isClosed()){
				closureSize++;
			}
		}
		assertEquals(1, closureSize);
	}
	
	@Test
	public void RobotTest(){
		double x=rmap.getRobot().getRobotLocation().getX();
		double y=rmap.getRobot().getRobotLocation().getY();
		Assert.assertEquals(120, x, 0.00001);
		Assert.assertEquals(0, y, 0.00001);
		
	}
	
}
