package map;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

// we need to add the robot and direction of the robot

// draw both of the original map and the map information of detecting 

public class drawmap extends JPanel{

	public void paint(Graphics g)
	{
		
		 readmap a =new readmap();
         a.LoadBoundary("OriginalFile.xml");
         mapinfor b= new mapinfor();
         int newmap[][]= b.returnmap();
         
         for(int i=0;i<b.row;i++)
         {
        	 for(int j=0;j<b.column;j++)
        	 {
        		 if(newmap[i][j]==0)
        		 {
        			 g.drawRect(2*i,2*j, 2, 2);
   
        		 }
        		 if(newmap[i][j]==1)
        		 {g.drawRect(2*i,2*j, 2, 2);
        		 g.setColor(Color.black);
        		  g.fillRect(2*i,2*j, 2, 2);
        		 }
        		 if(newmap[i][j]==2)	 
        		 {
        		 int m=0;
        		  while(m<a.disasternumber)
        		  {
        		  g.drawOval(2*i, 2*j, 2*a.disaster[m][2], 2*a.disaster[m][2]);
        		  g.setColor(Color.red);
        		  g.fillOval(2*i, 2*j, 2*a.disaster[m][2], 2*a.disaster[m][2]);
        		  }
        		 }
        		 if(newmap[i][j]==3)
        		 {g.drawRect(2*i,2*j, 2, 2);
        		 g.setColor(Color.darkGray);
        		  g.fillRect(2*i,2*j, 2, 2);
        		  }
        		 if(newmap[i][j]==4)
        		 {g.drawRect(2*i,2*j, 2, 2);
        		  g.setColor(Color.blue);
        		  g.fillRect(2*i,2*j, 2, 2);
        		 }
        		 if(newmap[i][j]==5)
        		 {g.drawRect(2*i,2*j, 2, 2);
        		  g.setColor(Color.green);
        		  g.fillRect(2*i,2*j, 2, 2);
        		  }
        		 if(newmap[i][j]==6)
        		  {g.drawRect(2*i,2*j, 2, 2);
        		   g.setColor(Color.gray);
        		   g.fillRect(2*i,2*j, 2, 2);
        		  }
        	 }	 
         }
	}
}
