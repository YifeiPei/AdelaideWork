import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

// we need to add the robot and direction of the robot

public class Draw extends JPanel{

	public void paint(Graphics g)
	{
		
		 readmap a =new readmap();
         a.LoadBoundary("OriginalFile.xml");
     // Draw the boundary
	     g.drawRect(0,0,a.boundary[0],a.boundary[1]);
	     g.setColor(Color.black);
	 // draw the unexplored place
	     for(int i=0;i<a.unexplorednumber;i++)
	     {
		 g.drawRect(a.unexplored[i][0],a.unexplored[i][1],a.unexplored[i][2],a.unexplored[i][3]);
		 g.fillRect(a.unexplored[i][0],a.unexplored[i][1],a.unexplored[i][2],a.unexplored[i][3]);
	     }
	 
	     g.setColor(Color.red);
	 //draw the disaster
	     for(int i=0;i<a.disasternumber;i++)
	     {
		 g.drawOval(a.disaster[i][0], a.disaster[i][1], a.disaster[i][2], a.disaster[i][2]);
		 g.fillOval(a.disaster[i][0], a.disaster[i][1], a.disaster[i][2], a.disaster[i][2]);
	     }
	     g.setColor(Color.magenta);
	 // draw the road
		 for(int i=0;i<a.roadnumber;i++)
		 {
			 g.drawLine(a.totalroad[i][0][0], a.totalroad[i][0][1], a.totalroad[i][1][0], a.totalroad[i][1][1]);
		 }
	 // draw the intersection points
		 g.setColor(Color.green);
		 for(int i=0;i<a.intersectionnumber;i++)
		 {
			 g.drawOval((int)(a.totalintersection[i][0]-2), (int)(a.totalintersection[i][1]-2), 4, 4);
			 g.fillOval((int)(a.totalintersection[i][0]-2), (int)(a.totalintersection[i][1]-2), 4, 4);
		 }
	 // draw the obstacle points
		 g.setColor(Color.orange);
		 for(int i=0;i<a.obstaclenumber;i++)
		 {
			 g.drawOval((int)(a.totalobstacle[i][0]-2), (int)(a.totalobstacle[i][1]-2), 4, 4);
			 g.fillOval((int)(a.totalobstacle[i][0]-2), (int)(a.totalobstacle[i][1]-2), 4, 4);
		 }
	 // draw the closure points
		 g.setColor(Color.lightGray);
		 
		 for(int i=0;i<a.closurenumber;i++)
		 {
			 g.drawOval((int)(a.totalclosure[i][0]-2), (int)(a.totalclosure[i][1]-2), 4, 4);
			 g.fillOval((int)(a.totalclosure[i][0]-2), (int)(a.totalclosure[i][1]-2), 4, 4);
		 }
	}
}
