package mapDataStructure;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @filename Road
 * @author Matthew Nestor
 *
 */

public class Road implements RobotLocation {
	private Point start;
	private Point end;
	private Point closure;
	private Point robotCoordinates;
	private boolean isClosed;
	private boolean containsRobot;
	private int robotOrientation;

	public Road(Point ps, Point pe){
		start = ps;
		end = pe;
		closure = null;
		robotCoordinates = null;
		isClosed = false;
	}
	
	public Point getStart(){
		return start;
	}
	
	public Point getEnd(){
		return end;
	}
	
	public boolean isClosed(){
		return isClosed;
	}
	
	public void setClosed(boolean b){
		isClosed = b;
	}
	
	public void setClosureLocation(Point p){
		closure = p;
	}
	
	public Point getClosureLocation(){
		return closure;
	}
	
	public boolean containsRobot(){
		return containsRobot;
	}
	
	public Point getRobotLocation(){
		return robotCoordinates;
	}
	
	public int getRobotOrientation(){
		return robotOrientation;
	}
	
	public void setContainsRobot(boolean b){
		containsRobot = b;
	}
	
	public void setRobotLocation(Point p){
		robotCoordinates = p;
	}
	
	public void setRobotOrientation(int i){
		robotOrientation = i;
	}

}
