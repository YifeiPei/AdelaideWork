import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

// we need to add the robot and direction of the robot

public class Draw extends JPanel{
	private String file;
	
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
	     g.drawRect(0,0,myreader.boundary[0],myreader.boundary[1]);
	     g.setColor(Color.black);
	 // draw the unexplored place
	     for(int i=0;i<myreader.unexplorednumber;i++)
	     {
		 g.drawRect(myreader.unexplored[i][0],myreader.unexplored[i][1],myreader.unexplored[i][2],myreader.unexplored[i][3]);
		 g.fillRect(myreader.unexplored[i][0],myreader.unexplored[i][1],myreader.unexplored[i][2],myreader.unexplored[i][3]);
	     }
	 
	     g.setColor(Color.red);
	 //draw the disaster
	     for(int i=0;i<myreader.disasternumber;i++)
	     {
		 g.drawOval(myreader.disaster[i][0], myreader.disaster[i][1], myreader.disaster[i][2], myreader.disaster[i][2]);
		 g.fillOval(myreader.disaster[i][0], myreader.disaster[i][1], myreader.disaster[i][2], myreader.disaster[i][2]);
	     }
	     g.setColor(Color.magenta);
	 // draw the road
		 for(int i=0;i<myreader.roadnumber;i++)
		 {
			 g.drawLine(myreader.totalroad[i][0][0], myreader.totalroad[i][0][1], myreader.totalroad[i][1][0], myreader.totalroad[i][1][1]);
		 }
	 // draw the intersection points
		 g.setColor(Color.green);
		 for(int i=0;i<myreader.intersectionnumber;i++)
		 {
			 g.drawOval((int)(myreader.totalintersection[i][0]-2), (int)(myreader.totalintersection[i][1]-2), 4, 4);
			 g.fillOval((int)(myreader.totalintersection[i][0]-2), (int)(myreader.totalintersection[i][1]-2), 4, 4);
		 }
	 // draw the obstacle points
		 g.setColor(Color.orange);
		 for(int i=0;i<myreader.obstaclenumber;i++)
		 {
			 g.drawOval((int)(myreader.totalobstacle[i][0]-2), (int)(myreader.totalobstacle[i][1]-2), 4, 4);
			 g.fillOval((int)(myreader.totalobstacle[i][0]-2), (int)(myreader.totalobstacle[i][1]-2), 4, 4);
		 }
	 // draw the closure points
		 g.setColor(Color.lightGray);
		 
		 for(int i=0;i<myreader.closurenumber;i++)
		 {
			 g.drawOval((int)(myreader.totalclosure[i][0]-2), (int)(myreader.totalclosure[i][1]-2), 4, 4);
			 g.fillOval((int)(myreader.totalclosure[i][0]-2), (int)(myreader.totalclosure[i][1]-2), 4, 4);
		 }
		 
		 g.setColor(Color.black);
		 g.drawString("The red area is disaster", 20, myreader.boundary[1]+30);
		 
		 g.drawString("The green point is intersection", 20, myreader.boundary[1]+50);
		 
		 g.drawString("The magenta line is road", 20, myreader.boundary[1]+70);
		 g.drawString("The orange point is obstacle", 20, myreader.boundary[1]+90);
		 g.drawString("The Gray point is closure", 20, myreader.boundary[1]+110);

	}
}
