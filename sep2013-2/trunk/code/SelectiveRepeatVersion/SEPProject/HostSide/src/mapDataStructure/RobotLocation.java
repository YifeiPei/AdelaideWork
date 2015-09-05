package mapDataStructure;

import java.awt.Point;

/**
 * @filename RobotLocation
 * @author Matthew Nestor
 *
 */

public interface RobotLocation {
	public boolean containsRobot(); //returns true if the robot is on this object
	public Point getRobotLocation(); //returns the coordinates of the robot
	public int getRobotOrientation(); //returns the robot's orientation
	public void setContainsRobot(boolean b);
	public void setRobotLocation(Point p);
	public void rotateRobot(String s);
	public void setRobotOrientation(int i);
}
