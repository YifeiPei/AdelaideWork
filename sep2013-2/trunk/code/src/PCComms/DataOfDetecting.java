
public class DataOfDetecting {
//	public static void main(String args[])
//	{
// Store the information which is detected by the robot
	int road[][][];
	int obstacle[][];
	int intersection[][];
	int disater[][];
	int clousure[][];
	int roadnumber=1;
	int obstaclenumber=1;
	int intersectionnumber=1;
	int disaternumber=1;
	int clousurenumber=1;
	float k;
	float x0 = 5;
	float y0 = 5;
	float x1 = 0;
	float y1 = 0;
	float x2=0;
	float y2=0;


	
	{	
    road =new int[1][2][2];
	road[0][0][0]=50;
	road[0][0][1]=50;
	road[0][1][0]=50;
	road[0][1][1]=50;
	obstacle=new int[1][2];
	obstacle[0][0]=100;
	obstacle[0][1]=100;
//	obstacle[1][0]=100;
//	obstacle[1][1]=100;
//	intersection= new int[1][2];
//	intersection[0][0]=50;
//	intersection[0][1]=50;
//	System.out.print(obstacle.length);
	k=new Float(Math.toRadians(90));  
	 x2=new Float((x1-x0)*Math.cos(k) +(y1-y0)*Math.sin(k)+x0);  
	 y2=new Float(-(x1-x0)*Math.sin(k) + (y1-y0)*Math.cos(k)+y0);

//	}
	}
	
}
