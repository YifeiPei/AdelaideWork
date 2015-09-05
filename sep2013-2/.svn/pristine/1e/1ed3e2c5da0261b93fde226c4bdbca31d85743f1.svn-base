package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import mapDataStructure.*;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Yifei Pei & Matthew Nestor
 *
 */

public class MapDraw extends JPanel{
	private final int L = 2;
	private Map map;
	private ArrayList<Obstacle> obstacles = null;
	private ArrayList<Road> roads = null;
	private ArrayList<Intersection> intersections = null;
	private ArrayList<UnexploredZone> unexploredZones = null;
	private ArrayList<DisasterZone> disasterZones = null;


	public MapDraw(Map m){
		map = m;
	}

	public void paintComponent(Graphics g)
	{
		System.out.println(SwingUtilities.isEventDispatchThread());
		super.paintComponent(g);
		// Draw the boundary
		
		g.drawRect(0,0,map.getWidth()*L,map.getHeight()*L);

		// draw the unexplored zones
		g.setColor(Color.black);
		unexploredZones = map.getUnexploredZones();
		for(UnexploredZone uz: unexploredZones){
			g.drawRect((int)(uz.getLocation().getX()*L), (int)(uz.getLocation().getY()*L), uz.getWidth()*L, uz.getHeight()*L);
			g.fillRect((int)(uz.getLocation().getX()*L), (int)(uz.getLocation().getY()*L), uz.getWidth()*L,uz.getHeight()*L);
		}
		
		//draw the disaster zones
		g.setColor(Color.red);
		disasterZones = map.getDisasterZones();
		for(DisasterZone dz: disasterZones){
			g.drawOval((int)((dz.getLocation().getX()-dz.getRadius())*L),
						(int)((dz.getLocation().getY()-dz.getRadius())*L),
							(dz.getRadius()*2)*L, (dz.getRadius()*2)*L);
			g.fillOval((int)((dz.getLocation().getX()-dz.getRadius())*L),
					(int)((dz.getLocation().getY()-dz.getRadius())*L),
					(dz.getRadius()*2)*L, (dz.getRadius()*2)*L);
		}
		
		// draw the roads
		g.setColor(Color.magenta);
		roads = map.getRoads();
		for(Road r: roads)
		{
			g.drawLine((int)(r.getStart().getX()*L), (int)(r.getStart().getY()*L),
						(int)(r.getEnd().getX()*L), (int)(r.getEnd().getY()*L));
		}
		
		// draw the intersections
		g.setColor(Color.green);
		intersections = map.getIntersections();
		for(Intersection i: intersections)
		{
			g.drawOval((int)(i.getLocation().getX()-2)*L, (int)(i.getLocation().getY()-2)*L, 8, 8);
			g.fillOval((int)(i.getLocation().getX()-2)*L, (int)(i.getLocation().getY()-2)*L, 8, 8);
		}
		
		// draw the obstacles
		g.setColor(Color.orange);
		obstacles = map.getObstacles();
		for(Obstacle o: obstacles)
		{
			g.drawOval((int)(o.getLocation().getX()-2)*L, (int)(o.getLocation().getY()-2)*L, 8, 8);
			g.fillOval((int)(o.getLocation().getX()-2)*L, (int)(o.getLocation().getY()-2)*L, 8, 8);
		}
		
		// draw the closures
		g.setColor(Color.lightGray);
		for(Road r: roads)
		{
			if(r.isClosed()){
				g.drawOval((int)(r.getClosureLocation().getX()-2)*L, (int)(r.getClosureLocation().getY()-2)*L, 8, 8);
				g.fillOval((int)(r.getClosureLocation().getX()-2)*L, (int)(r.getClosureLocation().getY()-2)*L, 8, 8);
			}
		}

		g.setColor(Color.black);
		g.drawString("The red areas are disaster zones", 20, map.getHeight()*L+30*L);

		g.drawString("The green points are intersections", 20, map.getHeight()*L+50*L);

		g.drawString("The magenta lines are roads", 20, map.getHeight()*L+70*L);
		g.drawString("The orange points are obstacles", 20, map.getHeight()*L+90*L);
		g.drawString("The gray points are road closures", 20, map.getHeight()*L+110*L);

		// Draw the robot
		int [] r_x = {(int)Math.round(-(20)),(int)Math.round(6.5),(int)Math.round((6.5+3)),(int)Math.round(6.5),(int)Math.round(-20)};
		int [] r_y = {(int)Math.round(-8),(int)Math.round(-8),0,(int)Math.round(8),(int)Math.round(8)};
		Polygon robot = new Polygon(r_x, r_y, 5);

		// 1 radian = 57.2957795 degree, below is using radian
		Graphics2D g2d = (Graphics2D) g;
		int x = (int)(map.getRobot().getRobotLocation().getX()*L);
		int y = (int)(map.getRobot().getRobotLocation().getY()*L);
		double turn = (int)map.getRobot().getRobotOrientation();
		g2d.setColor(Color.blue);
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(turn), 0, 0);

		Shape transformed = transform.createTransformedShape(robot);
		g2d.translate(x,y);
		g2d.draw(transformed);
		g2d.fill(transformed);
		g2d.translate(-x, -y);//revert translate


	}



}