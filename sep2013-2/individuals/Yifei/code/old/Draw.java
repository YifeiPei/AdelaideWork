package PCComms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;


// we need to add the robot and direction of the robot

public class Draw extends JPanel{
	private String file;
	private final int L = 2;
	
	public Draw(String f){
		file = f;
		System.out.println(file);
	}
	
	public void paint(Graphics g)
	{
		 super.paint(g);
		 ReadMap myreader =new ReadMap();
		 myreader.LoadBoundary(file);
     // Draw the boundary
	     g.drawRect(0,0,myreader.boundary[0]*L,myreader.boundary[1]*L);
	     g.setColor(Color.black);
	     
	     
	     
	 // draw the unexplored place
	     for(int i=0;i<myreader.unexplorednumber;i++)
	     {
		 g.drawRect(myreader.unexplored[i][0]*L,myreader.unexplored[i][1]*L,myreader.unexplored[i][2]*L,myreader.unexplored[i][3]*L);
		 g.fillRect(myreader.unexplored[i][0]*L,myreader.unexplored[i][1]*L,myreader.unexplored[i][2]*L,myreader.unexplored[i][3]*L);
	     }
	 
	     g.setColor(Color.red);
	 //draw the disaster
	     for(int i=0;i<myreader.disasternumber;i++)
	     {
		 g.drawOval(myreader.disaster[i][0]*L, myreader.disaster[i][1]*L, myreader.disaster[i][2]*L, myreader.disaster[i][2]*L);
		 g.fillOval(myreader.disaster[i][0]*L, myreader.disaster[i][1]*L, myreader.disaster[i][2]*L, myreader.disaster[i][2]*L);
	     }
	     g.setColor(Color.magenta);
	 // draw the road
		 for(int i=0;i<myreader.roadnumber;i++)
		 {
			 g.drawLine(myreader.totalroad[i][0][0]*L, myreader.totalroad[i][0][1]*L, myreader.totalroad[i][1][0]*L, myreader.totalroad[i][1][1]*L);
		 }
	 // draw the intersection points
		 g.setColor(Color.green);
		 for(int i=0;i<myreader.intersectionnumber;i++)
		 {
			 g.drawOval((int)(myreader.totalintersection[i][0]-2)*L, (int)(myreader.totalintersection[i][1]-2)*L, 8, 8);
			 g.fillOval((int)(myreader.totalintersection[i][0]-2)*L, (int)(myreader.totalintersection[i][1]-2)*L, 8, 8);
		 }
	 // draw the obstacle points
		 g.setColor(Color.orange);
		 for(int i=0;i<myreader.obstaclenumber;i++)
		 {
			 g.drawOval((int)(myreader.totalobstacle[i][0]-2)*L, (int)(myreader.totalobstacle[i][1]-2)*L, 8, 8);
			 g.fillOval((int)(myreader.totalobstacle[i][0]-2)*L, (int)(myreader.totalobstacle[i][1]-2)*L, 8, 8);
		 }
	 // draw the closure points
		 g.setColor(Color.lightGray);
		 
		 for(int i=0;i<myreader.closurenumber;i++)
		 {
			 g.drawOval((int)(myreader.totalclosure[i][0]-2)*L, (int)(myreader.totalclosure[i][1]-2)*L, 8, 8);
			 g.fillOval((int)(myreader.totalclosure[i][0]-2)*L, (int)(myreader.totalclosure[i][1]-2)*L, 8, 8);
		 }
		 
		 g.setColor(Color.black);
		 g.drawString("The red area is disaster", 20, myreader.boundary[1]*L+30*L);
		 
		 g.drawString("The green point is intersection", 20, myreader.boundary[1]*L+50*L);
		 
		 g.drawString("The magenta line is road", 20, myreader.boundary[1]*L+70*L);
		 g.drawString("The orange point is obstacle", 20, myreader.boundary[1]*L+90*L);
		 g.drawString("The Gray point is closure", 20, myreader.boundary[1]*L+110*L);
		 
		 // Draw the robot
	     int [] r_x = {(int)Math.round(-(20)),(int)Math.round(6.5),(int)Math.round((6.5+3)),(int)Math.round(6.5),(int)Math.round(-20)};
	     int [] r_y = {(int)Math.round(-8),(int)Math.round(-8),0,(int)Math.round(8),(int)Math.round(8)};
	     Polygon robot = new Polygon(r_x, r_y, 5);
	     
	   // 1 radian = 57.2957795 degree, below is using radian
	    Graphics2D g2d = (Graphics2D) g;
	    int x = myreader.robot[0]*L;
	    int y = myreader.robot[1]*L;
	    double turn = myreader.robot[2];
	    g2d.setColor(Color.blue);
	    AffineTransform transform = new AffineTransform();
	    transform.rotate(Math.toRadians(turn), 0,0 );
	    
	    Shape transformed = transform.createTransformedShape(robot);
	    g2d.translate(x,y);
	    g2d.draw(transformed);
	    g2d.fill(transformed);
	    g2d.translate(-x, -y);//revert translate
	    			
	    repaint();

	}
	


}
