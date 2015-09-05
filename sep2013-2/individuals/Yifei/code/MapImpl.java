package PCComms;

import java.util.ArrayList;

public class MapImpl implements Map {
	
    Mapdata[][] mapPoints;
    Mapdata point;
    double x;
    double y;
    double facing;//in radian, 1 radian = 57.2957795 degree
    ArrayList<Disaster> disasterZones;
    
    public MapImpl() {
		this(240,180,3,3); //size of XML map is 240*180, size of a1 is 849*594, tile size is 3, so the map size is 720*540.
    }
    
    public MapImpl(int x, int y) {
    	this(x,y,3,3);
    }
    
	public MapImpl(int x, int y, int xScale, int yScale){
		disasterZones = new ArrayList<Disaster> ();
		facing = 0.0;
		mapPoints = new Mapdata[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                mapPoints[i][j] = new Mapdata(); 
                mapPoints[i][j].isUnexploredArea = false;
                mapPoints[i][j].isRoad = false;
                mapPoints[i][j].isIntersection = false;
                mapPoints[i][j].isObstacle = false;
                mapPoints[i][j].isDisaster = false;
                mapPoints[i][j].isRobotLocation = false;
            }
        }
     setXY(x*xScale,y*yScale);
    }

	@Override
	public Mapdata[][] getMap() {
		// TODO Auto-generated method stub
		return mapPoints;
	}

	@Override
	public double getXSize() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getYSize() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public Mapdata getMapPoint(int x, int y) {
		// TODO Auto-generated method stub
		Mapdata pointAt = mapPoints[x][y];
        return pointAt;
	}

	@Override
	public void setMapPoint(int x, int y, boolean isUnexploredArea,
			boolean isRoad, boolean isIntersection, boolean isObstacle,
			boolean isDisaster, boolean isRobotLocation) {
		// TODO Auto-generated method stub
        Mapdata pointAt = getMapPoint(x, y);
        pointAt.isUnexploredArea = isUnexploredArea;
        pointAt.isRoad = isRoad;
        pointAt.isIntersection = isIntersection;
        pointAt.isObstacle = isObstacle;
        pointAt.isDisaster = isDisaster;
        pointAt.isRobotLocation = isRobotLocation;

	}

	@Override
	public void addDisaster(Disaster disaster) {
		// TODO Auto-generated method stub
		disasterZones.add(disaster);
	}

	@Override
	public ArrayList<Disaster> getDisaster() {
		// TODO Auto-generated method stub
		return disasterZones;
	}

	@Override
	public void clearDisaster(Disaster disaster) {
		// TODO Auto-generated method stub
		disasterZones.remove(disaster);
	}

	@Override
	public void clearAllDisaster() {
		// TODO Auto-generated method stub
		disasterZones.clear();
	}

	@Override
	public double getRobotFacing() {
		// TODO Auto-generated method stub
		return facing;
	}

	@Override
	public void setRobotFacing(double angle) {
		// TODO Auto-generated method stub
		this.facing = angle;
	}
	
    public void setXY(int w, int h) {
        x = (double) w;
        y = (double) h;
    }

}
