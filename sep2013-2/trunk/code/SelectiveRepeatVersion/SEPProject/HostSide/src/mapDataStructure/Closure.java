package mapDataStructure;

import java.awt.Point;

/**
 * @author Matthew Nestor
 * @filename Closure.java
 * @package mapDataStructure
 * @project HostSide
 * @date 16/10/2013
 */

public class Closure {
	private Point location;
	
	public Closure(Point p){
		location = p;
	}
	
	public Point getLocation(){
		return location;
	}
}
