package HostSide.mapDataStructure;

import java.awt.Point;

/**
 * @filename DisasterZone
 * @author Matthew Nestor
 *
 */

public class DisasterZone {
	private Point location;
	private int radius;
	
	public DisasterZone(Point p, int rad){
		location = p;
		radius = rad;
	}
	
	public Point getLocation(){
		return location;
	}
	
	public int getRadius(){
		return radius;
	}
}
