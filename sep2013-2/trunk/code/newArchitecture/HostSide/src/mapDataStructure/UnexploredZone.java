package mapDataStructure;

import java.awt.Point;

/**
 * @author Matthew Nestor
 * @filename UnexploredZone.java
 * @package mapDataStructure
 * @project HostSide
 * @date 16/10/2013
 */

public class UnexploredZone {
	//unexplored area
	private Point location;
	private int width;
	private int height;
	
	public UnexploredZone(Point p, int w, int h){
		location = p;
		width = w;
		height = h;
	}
	
	public Point getLocation(){
		return location;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean containsRobot(Point p){
		if(p.getX() < location.getX())
			return false;
		if(p.getY() < location.getY())
			return false;
		if(p.getX() > (location.getX() + width))
			return false;
		if(p.getY() > (location.getY() + height))
			return false;
		
		return true;
	}
}
