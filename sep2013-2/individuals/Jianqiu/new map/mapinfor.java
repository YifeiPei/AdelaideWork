package map;

public class mapinfor {
	
  readmap mapobject = new readmap();
  public int row =mapobject.boundary[0];
  public int column=mapobject.boundary[1];
  public int map[][] =new int[row][column];
   public int[][] returnmap()
   {
	 for(int i=0;i<row;i++)
	 {
		 for(int j=0;j<column;j++)
		 {
			 map[i][j]=0;
             			 
		 }
		 
	 }
	 
	 for(int i=mapobject.unexplored[0][0];i<mapobject.unexplored[0][2];i++)
	 {
		 for(int j=mapobject.unexplored[0][1];j<mapobject.unexplored[0][3];j++)
		 {
			 map[i][j]=1;		 
		 }
		 
	 }
	 
	 int m=0;
	 while(m<mapobject.disasternumber)
	 {
		map[mapobject.disaster[m][0]][mapobject.disaster[m][1]]=2;
		m++;
	 }
	 
	 m=0;
	 while(m<mapobject.intersectionnumber)
	 {
		map[mapobject.totalintersection[m][0]][mapobject.totalintersection[m][1]]=3;
		m++;
	 } 
	 m=0;
	 while(m<mapobject.obstaclenumber)
	 {
		map[mapobject.totalobstacle[m][0]][mapobject.totalobstacle[m][1]]=4;
		m++;
	 } 
	 while(m<mapobject.closurenumber)
	 {
		map[mapobject.totalclosure[m][0]][mapobject.totalclosure[m][1]]=5;
		m++;
	 } 
	 
	 // has bug only straight road 
	 while(m<mapobject.roadnumber)
	 {
		 int xs =Math.min(mapobject.totalroad[m][0][0], mapobject.totalroad[m][1][0]);
		 int xm =Math.max(mapobject.totalroad[m][0][0], mapobject.totalroad[m][1][0]);
		 int ys =Math.min(mapobject.totalroad[m][0][1], mapobject.totalroad[m][1][1]);
		 int ym =Math.max(mapobject.totalroad[m][0][1], mapobject.totalroad[m][1][1]);
		 
		 for(int i=xs;i<xm;i++)
		 {
			 for(int j=ys;j<ym;j++)
			 {	 
				 map[i][j]=6;
			 }
		 } 
	 }
	return map; 
   }

}
