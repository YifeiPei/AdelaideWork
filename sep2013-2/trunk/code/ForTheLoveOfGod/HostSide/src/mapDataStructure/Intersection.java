package mapDataStructure;

import mapDataStructure.Road;
import java.util.ArrayList;
import java.awt.Point;

/**
 * @author Matthew Nestor
 * @filename Intersection.java
 * @package mapDataStructure
 * @project HostSide
 * @date 16/10/2013
 */

public class Intersection implements RobotLocation{
	private Point location;
	private Point robotCoordinates;
	private boolean isClosed;
	private boolean containsRobot;
	private int robotOrientation;
	
	public Intersection(Point p){
		location = p;
	}
	
	public Point getLocation(){
		return location;
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
	
	public void rotateRobot(String s){
		if(s.equals("LEFT")){
			switch(robotOrientation){
			case 0:
				setRobotOrientation(90);
			case 90:
				setRobotOrientation(180);
			case 180:
				setRobotOrientation(270);
			case 270:
				setRobotOrientation(0);
			}
		}else if(s.equals("RIGHT")){
			switch(robotOrientation){
			case 0:
				setRobotOrientation(270);
			case 90:
				setRobotOrientation(0);
			case 180:
				setRobotOrientation(90);
			case 270:
				setRobotOrientation(180);
			}
		}
	}
}
