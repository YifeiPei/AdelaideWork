package HostSide.mapDataStructure;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Yu Hong
 * @filename RobotMapTest.java
 */
public class RobotMapTest {

	RobotMap rmap;
	
	@Before
	public void before(){
		rmap = new RobotMap();
		rmap.loadMap("src/mapTest/map.xml");
	}
	
	@Test
	public void disaterTest() {	
		int disaterSize =rmap.getDisasterZones().size();
		double x =rmap.getDisasterZones().get(0).getLocation().getX();
		double y =rmap.getDisasterZones().get(0).getLocation().getY();
		Assert.assertEquals(70, x, 0.00001);
		Assert.assertEquals(40, y, 0.00001);
		assertEquals(1, disaterSize);
	}
	
	@Test
	public void intersectionTest() {
		int intersectionSize =rmap.getIntersections().size();
		double x =rmap.getIntersections().get(0).getLocation().getX();
		double y =rmap.getIntersections().get(0).getLocation().getY();
		Assert.assertEquals(120, x, 0.00001);
		Assert.assertEquals(40, y, 0.00001);
		assertEquals(1, intersectionSize);
	}
	
	@Test
	public void obstaclesTest() {
		int obstacleSize =rmap.getObstacles().size();
		double x =rmap.getObstacles().get(0).getLocation().getX();
		double y =rmap.getObstacles().get(0).getLocation().getY();
		Assert.assertEquals(160, x, 0.00001);
		Assert.assertEquals(40, y, 0.00001);
		assertEquals(1, obstacleSize);
	}
	
	@Test
	public void roadsTest() {
		int roadSize =rmap.getRoads().size();
		double start =rmap.getRoads().get(0).getStart().getX();
		double end =rmap.getRoads().get(0).getStart().getY();
		double start2 =rmap.getRoads().get(0).getEnd().getX();
		double end2 =rmap.getRoads().get(0).getEnd().getY();
		Assert.assertEquals(120, start, 0.00001);
		Assert.assertEquals(0, end, 0.00001);
		Assert.assertEquals(120, start2, 0.00001);
		Assert.assertEquals(40, end2, 0.00001);
		assertEquals(2, roadSize);
	}
	
	@Test
	public void unexploredZonesTest() {
		int unexploredZoneSize =rmap.getUnexploredZones().size();
		double x =rmap.getUnexploredZones().get(0).getLocation().getX();
		double y =rmap.getUnexploredZones().get(0).getLocation().getY();

		Assert.assertEquals(0, x, 0.00001);
		Assert.assertEquals(41, y, 0.00001);

		assertEquals(1, unexploredZoneSize);
	}
	
	@Test
	public void closureTest() {
		int closureSize = 0;
		ArrayList <Road> roads = new ArrayList();
		roads = rmap.getRoads();
		for (Road r: roads){
			if (r.isClosed()){
				closureSize++;
			}
		}
		assertEquals(1, closureSize);
	}
	
}
