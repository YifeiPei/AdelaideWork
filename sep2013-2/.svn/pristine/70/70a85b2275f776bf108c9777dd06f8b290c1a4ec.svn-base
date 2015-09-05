package PCComms;

import java.awt.Point;
import java.util.ArrayList;


public interface Map {

	//get the Map object
	public Mapdata[][] getMap(); 

	//get the Actual X length of the area being mapped
	public double getXSize();
	
	//get the Actual Y length of the area being mapped
	public double getYSize();
	
	//get the Mapdata object at position (x,y)
	public Mapdata getMapPoint(int x, int y);
	
	//set information about Mapdata at position (x,y)
	public void setMapPoint(int x, int y,
			boolean isUnexploredArea,
			boolean isRoad,
			boolean isIntersection,
			boolean isObstacle,
			boolean isDisaster,
			boolean isRobotLocation);
	
	//sets area as defined in disaster zone
	public void addDisaster (Disaster disaster);
	
	//returns all the disaster zones in a map
	public ArrayList<Disaster> getDisaster();
	
	//remove specified disaster zone from the map
	public void clearDisaster(Disaster disaster);

	//remove all disaster zones from the map
	public void clearAllDisaster();
	
	//return direction robot is facing 0-360, where 0 is in the positive x direction.
	public double getRobotFacing();
	
	//set robotfacing
	public void setRobotFacing(double angle);

}
