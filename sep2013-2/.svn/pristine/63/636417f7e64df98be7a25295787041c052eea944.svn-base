import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class readmap
{
	int[] boundary= new int[2];
    int[][] unexplored;
    int unexplorednumber;
    int[][] disaster;
    int disasternumber;
    int roadnumber;
    int[][][] totalroad;
    int intersectionnumber;
    int[][] totalintersection;
    int obstaclenumber;
    int[][] totalobstacle;
    int closurenumber;
    int[][] totalclosure;
    
	public void LoadBoundary (String a)
	{

	try{
	// A is the map file name 
	//C:\\Users\\Justin\\workspace\\Mapping\\src\\ is the route of the store place of the file
	File fXmlFile = new File("C:\\Users\\Justin\\workspace\\Mapping\\src\\"+a);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
	// The list of rect area
	NodeList rectarea = doc.getElementsByTagName("rect");
	// The number of rect area in the list
	int rectlength =rectarea.getLength();
    //Find the map boundary from the list, the boundary must be the first rect in XML file
	Node n =rectarea.item(0);
	Element e =(Element)n;
	//get the width and height from XML
	boundary[0] =Integer.parseInt(e.getAttribute("width"));
	boundary[1] =Integer.parseInt(e.getAttribute("height"));
	//get the node of zone area (unexplored)
	unexplorednumber=rectlength-1;
	if(rectlength>1)
	{
		
		unexplored =new int[rectlength-1][4];
	for(int i=1;i<rectlength;i++)
	{
		Node m =rectarea.item(i);
		
		if (m.getNodeType() == Node.ELEMENT_NODE)
		{
			Element f =(Element)m;
		
			unexplored[i-1][0]=Integer.parseInt(f.getAttribute("x"));
			unexplored[i-1][1]=Integer.parseInt(f.getAttribute("y"));
			unexplored[i-1][2]=Integer.parseInt(f.getAttribute("width"));
			unexplored[i-1][3]=Integer.parseInt(f.getAttribute("height"));
		}
	}
	}
	//get the list of disaster 
	NodeList disasterarea = doc.getElementsByTagName("circle");
    disasternumber =disasterarea.getLength();
    disaster =new int[disasternumber][3];
    for(int i=0;i<disasternumber;i++)
	{
		Node d =disasterarea.item(i);
		if (d.getNodeType() == Node.ELEMENT_NODE)
		{
			Element delement =(Element)d;
			disaster[i][0]=Integer.parseInt(delement.getAttribute("x"));
			disaster[i][1]=Integer.parseInt(delement.getAttribute("y"));
			disaster[i][2]=Integer.parseInt(delement.getAttribute("radius"));
		}	
	}
	
    // get the point of the road
    NodeList road = doc.getElementsByTagName("road");
    // the number of roads
	roadnumber=road.getLength();
	// use to store the two points in g-th road 
	totalroad =new int[roadnumber][2][2];
    // get the two points of each road
	int g=0;
	while(g<roadnumber)
	{
   // the g-th road
		Node roadline =road.item(g);
		Element e_road =(Element)roadline;
		//the points in g-th road
		NodeList roadpoints =e_road.getElementsByTagName("point");
		// get the location of points
		for(int j=0;j<roadpoints.getLength();j++)
		{
			Node points =roadpoints.item(j);
			
			if (points.getNodeType() == Node.ELEMENT_NODE)
			{
				Element epoints =(Element)points;
				totalroad[g][j][0]=Integer.parseInt(epoints.getAttribute("x"));
				totalroad[g][j][1]=Integer.parseInt(epoints.getAttribute("y"));
			}	
			
		}
    g++;
	}
	
	// get the point of intersection 
	NodeList intersection = doc.getElementsByTagName("intersection");
	// the number of intersection
	intersectionnumber =intersection.getLength();
	// the matrix which is used to store the intersection points
	totalintersection =new int[intersectionnumber][2];
	int g1=0;
	while(g1<intersectionnumber)
	{
		//get the node of the g1-th
		Node interpoints =intersection.item(g1);
		Element e_inter =(Element)interpoints;
		//get the list of point node under the g1-th node
		NodeList  inter_point=e_inter.getElementsByTagName("point");
		// get the node
		Node i_point =inter_point.item(0);
		if (i_point.getNodeType() == Node.ELEMENT_NODE)
		{
			Element e_points =(Element)i_point;
			totalintersection[g1][0]=Integer.parseInt(e_points.getAttribute("x"));
			totalintersection[g1][1]=Integer.parseInt(e_points.getAttribute("y"));
		}	
		g1++;
	}
	
	// get the point of obstacle 
	NodeList obstacle = doc.getElementsByTagName("obstacle");
	// the number of obstacle 
	obstaclenumber = obstacle.getLength();
	// the matrix which is used to store the obstacle points
	totalobstacle =new int[obstaclenumber][2];
	int g2=0;
	while(g2<obstaclenumber)
	{
		//get the node of the g2-th
		Node obstaclepoints=obstacle.item(g2);
		Element e_obstacle =(Element)obstaclepoints;
		//get the list of point node under the g2-th node
		NodeList obstacle_point=e_obstacle.getElementsByTagName("point");
		//get the node
		Node o_point=obstacle_point.item(0);
		if (o_point.getNodeType() == Node.ELEMENT_NODE)
		{
			Element o_points =(Element)o_point;
			totalobstacle[g2][0]=Integer.parseInt(o_points.getAttribute("x"));
			totalobstacle[g2][1]=Integer.parseInt(o_points.getAttribute("y"));
		}	
		g2++;
	}
	    // get the point of closure
		NodeList closure = doc.getElementsByTagName("closure");
		// the number of closure 
		closurenumber = closure.getLength();
		// the matrix which is used to store the closure points
		totalclosure =new int[closurenumber][2];
		int g3=0;
		while(g3<closurenumber)
		{
			//get the node of the g3-th
			Node closurepoints=closure.item(g3);
			Element e_closure =(Element)closurepoints;
			//get the list of point node under the g3-th node
			NodeList closure_point=e_closure.getElementsByTagName("point");
			//get the node
			Node c_point=closure_point.item(0);
			if (c_point.getNodeType() == Node.ELEMENT_NODE)
			{
				Element c_points =(Element)c_point;
				totalclosure[g3][0]=Integer.parseInt(c_points.getAttribute("x"));
				totalclosure[g3][1]=Integer.parseInt(c_points.getAttribute("y"));
			}	
			g3++;
		}
	} catch (Exception e) {
	e.printStackTrace();
	
    }
	

	
}
	
	
	
	
	
	
	
	
	
	
}
