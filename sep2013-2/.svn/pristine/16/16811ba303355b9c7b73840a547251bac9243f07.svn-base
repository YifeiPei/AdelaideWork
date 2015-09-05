package HostSide.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.util.ArrayList;

import HostSide.mapDataStructure.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Yifei Pei & Matthew Nestor
 *
 */

public class MapDraw extends JPanel{
	private int scale;
	private Map map;
	private ArrayList<Obstacle> obstacles = null;
	private ArrayList<Road> roads = null;
	private ArrayList<Intersection> intersections = null;
	private ArrayList<UnexploredZone> unexploredZones = null;
	private ArrayList<DisasterZone> disasterZones = null;


	public MapDraw(Map m){
		map = m;
		this.setBackground(Color.WHITE);
		this.setBounds(247, 13, 1300, 750); //size of the map 1300x750
	}

	public void paintComponent(Graphics g)
	{
		final int radius = 4; // the radius for intersection, road closure, and obstcle
		System.out.println(SwingUtilities.isEventDispatchThread());
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		double calculate_scale_factor = Math.min( ((double)1300) / ((double)map.getWidth()), ((double)750) / ((double)map.getHeight()) );
		scale = (int) Math.floor(calculate_scale_factor);
		g2d.scale(scale, scale);

		//System.out.println(map.getWidth());
		//System.out.println(map.getHeight());
		//System.out.println(this.getWidth());
		//System.out.println(this.getHeight());

		BufferedImage unexplored = null;
		BufferedImage closure = null;
		BufferedImage obstacle = null;

		try {
			unexplored = ImageIO.read(new File("icon/unexplored.jpg"));
			closure = ImageIO.read(new File("icon/closure.png"));
			obstacle = ImageIO.read(new File("icon/obstacle.jpg"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		// Draw the boundary

		g2d.drawRect(0,0,map.getWidth(),map.getHeight());

		// draw the unexplored zones
		
		unexploredZones = map.getUnexploredZones();
		for(UnexploredZone uz: unexploredZones){
			g2d.drawImage (unexplored, (int)(uz.getLocation().getX()), (int)((map.getHeight() - uz.getLocation().getY()) - uz.getHeight()), uz.getWidth(), uz.getHeight(), null);
		}

		//g2d.setColor(Color.GRAY);
		//unexploredZones = map.getUnexploredZones();
		//for(UnexploredZone uz: unexploredZones){
		//	g2d.drawRect((int)(uz.getLocation().getX()), (int)((map.getHeight() - uz.getLocation().getY()) - uz.getHeight()), uz.getWidth(), uz.getHeight());
		//	g2d.fillRect((int)(uz.getLocation().getX()), (int)((map.getHeight() - uz.getLocation().getY()) - uz.getHeight()), uz.getWidth(), uz.getHeight());	
		//}

		//draw the disaster zones
		g2d.setColor(Color.red);
		disasterZones = map.getDisasterZones();
		for(DisasterZone dz: disasterZones){
			g2d.drawOval((int)((dz.getLocation().getX()-dz.getRadius())),
					(int)(((map.getHeight() - dz.getLocation().getY())-dz.getRadius())),
					(dz.getRadius()*2), (dz.getRadius()*2));
			g2d.fillOval((int)((dz.getLocation().getX()-dz.getRadius())),
					(int)(((map.getHeight() - dz.getLocation().getY())-dz.getRadius())),
					(dz.getRadius()*2), (dz.getRadius()*2));
		}

		// draw the roads
		g2d.setColor(Color.black);
		roads = map.getRoads();
		for(Road r: roads)
		{
			g2d.drawLine((int)(r.getStart().getX()), (int)(map.getHeight() - r.getStart().getY()),
					(int)(r.getEnd().getX()), (int)(map.getHeight() - r.getEnd().getY()));
		}

		// draw the intersections
		g2d.setColor(Color.black);
		intersections = map.getIntersections();
		for(Intersection i: intersections)
		{
			g2d.drawOval((int)(i.getLocation().getX()-radius), (int)((map.getHeight() - i.getLocation().getY())-radius), radius*2, radius*2);
			g2d.fillOval((int)(i.getLocation().getX()-radius), (int)((map.getHeight() - i.getLocation().getY())-radius), radius*2, radius*2);
		}

		// draw the obstacles
		
		obstacles = map.getObstacles();
		for(Obstacle o: obstacles) {
			g2d.drawImage (obstacle,(int)(o.getLocation().getX()-radius),(int)((map.getHeight() - o.getLocation().getY())-radius), radius*2, radius*2, null);
		}
		
		//g2d.setColor(Color.lightGray);
		//obstacles = map.getObstacles();
		//for(Obstacle o: obstacles)
		//{
		//	g2d.drawOval((int)(o.getLocation().getX()-radius), (int)((map.getHeight() - o.getLocation().getY())-radius), radius*2, radius*2);
		//	g2d.fillOval((int)(o.getLocation().getX()-radius), (int)((map.getHeight() - o.getLocation().getY())-radius), radius*2, radius*2);
		//}

		// draw the closures
		
		for(Road r: roads) {
			if(r.isClosed()) {
				g2d.drawImage (closure, (int)(r.getClosureLocation().getX()-radius), (int)((map.getHeight() - r.getClosureLocation().getY())-radius), radius*2, radius*2, null);
			}
		}
		
		
		//g2d.setColor(Color.orange);
		//for(Road r: roads)
		//{
		//	if(r.isClosed()){
		//		g2d.drawOval((int)(r.getClosureLocation().getX()-radius), (int)((map.getHeight() - r.getClosureLocation().getY())-radius), radius*2, radius*2);
		//		g2d.fillOval((int)(r.getClosureLocation().getX()-radius), (int)((map.getHeight() - r.getClosureLocation().getY())-radius), radius*2, radius*2);
		//	}
		//}

		//g.setColor(Color.black);
		//g.drawString("The red areas are disaster zones", 20, map.getHeight()+30);

		//g.drawString("The green points are intersections", 20, map.getHeight()+50);

		//g.drawString("The magenta lines are roads", 20, map.getHeight()+70);
		//g.drawString("The orange points are obstacles", 20, map.getHeight()+90);
		//g.drawString("The gray points are road closures", 20, map.getHeight()+110);

		// Draw the robot
		int [] r_x = {(int)Math.round(-(20)),(int)Math.round(6.5),(int)Math.round((6.5+3)),(int)Math.round(6.5),(int)Math.round(-20)};
		int [] r_y = {(int)Math.round(-8),(int)Math.round(-8),0,(int)Math.round(8),(int)Math.round(8)};
		Polygon robot = new Polygon(r_x, r_y, 5);

		// 1 radian = 57.2957795 degree, below is using radian

		int x = (int)(map.getRobot().getRobotLocation().getX());
		int y = (int)(map.getHeight() - map.getRobot().getRobotLocation().getY());
		double turn = (int)(180 + map.getRobot().getRobotOrientation());
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