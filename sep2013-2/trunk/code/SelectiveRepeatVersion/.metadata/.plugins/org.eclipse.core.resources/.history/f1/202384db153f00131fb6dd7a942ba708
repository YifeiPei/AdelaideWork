package mapDataStructure;

/**
 * @author Matthew Nestor
 */

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public interface Map {
	public void loadMap(String s);
	public void saveMap(String s);
	public void print();
	public int getWidth();
	public int getHeight();
	public ArrayList<Obstacle> getObstacles();
	public ArrayList<Road> getRoads();
	public ArrayList<Intersection> getIntersections();
	public ArrayList<UnexploredZone> getUnexploredZones();
	public ArrayList<DisasterZone> getDisasterZones();
	public RobotLocation getRobot();
	public void setGUIComponent(JPanel jp);
	public void addObstacle(Point p);
	public void addRoad(Point p1, Point p2);
	public void addIntersection(Point p);
	public void hasChanged();
}
