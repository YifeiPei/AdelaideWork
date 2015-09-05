package hostSideCommsUnit;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import controllerFSM.ControllerFSM;
import mapDataStructure.Map;
import mapDataStructure.RobotMap;
import mapDataStructure.UnexploredZone;

/**
 * The class is used to handle the message sent from the robot.
 * @author Yu Hong
 *
 */
public class PCReceiver {

	private int receiveMessage;
	private int lightValue;
	private int battery;
	private int tachoA;
	private int tachoB;
	private int distance;
	private int travelAngel;
	private int incrementDistance;
	private ControllerFSM controllerFSM;
	private Map map;
	private ArrayList<UnexploredZone> unexploredZones;
	
	/**
	 * the constructor of the class
	 */
	public PCReceiver(){
		battery = 0;
	}
	
	public void setFSM(ControllerFSM fsm){
		controllerFSM = fsm;
	}
	
	public void setMap(Map m){
		map = m;
	}
	
	public void connectionFailure() throws InterruptedException{
		controllerFSM.ConnectionFailure();
	}
	
	/**
	 * the method is used to handle the message sent from the robot side.
	 * @param rm the concrete message from the robot and it is represented
	 * as integers.
	 */
	public void messageHandler(int rm){
		receiveMessage = rm;
	
		if (receiveMessage<200 && receiveMessage > 100){
			lightValue = receiveMessage - 100;
			// System.out.println("lightValue: "+ lightValue);
		}
		if (receiveMessage<300 && receiveMessage >= 200){
			battery = receiveMessage - 200;
		}
		if (receiveMessage<400 && receiveMessage >= 300){
			distance = receiveMessage -300;
		}
		
		if (receiveMessage<0){
			tachoA = receiveMessage + 1000;
			System.out.println("tacho: "+ tachoA);
			//update robot location here
			
			//then update unexplored zones...
			unexploredZones = map.getUnexploredZones();
			Iterator<UnexploredZone> itr = unexploredZones.iterator();
			while(itr.hasNext()){
				UnexploredZone uz = itr.next();
				if(uz.containsRobot(map.getRobot().getRobotLocation()))
					itr.remove();
			}
			
			//update GUI
			map.hasChanged();
		}
		if (receiveMessage>5000 && receiveMessage<15000){
			travelAngel = receiveMessage - 10000;
			System.out.println("travelAngel: "+ travelAngel);
		}
		if (receiveMessage> 15000 && receiveMessage < 25000){
			incrementDistance = receiveMessage - 20000;
			System.out.println("incrementDistance: "+ incrementDistance);
			setRobotLocation(incrementDistance);
		}
	}
	
	public void setRobotLocation(int dis){
		Point p = new Point();
		double x,y;
		p = map.getRobot().getRobotLocation();
		x = p.getX();
		y = p.getY();
		if (dis != 0){
			y = y+dis;
			p.setLocation(x, y);
			map.getRobot().setRobotLocation(p);
			map.hasChanged();
		}
		int orientation =map.getRobot().getRobotOrientation();
		System.out.print(orientation);
	}
	
	/**
	 * to get the distance between the robot and the obstacle
	 * @return the distance
	 */
	public int getDistance(){
		return distance;
	}
	
	/**
	 * to get the tacho value from the robot
	 * @return tacho value
	 */
	public int getTachoValue(){
		return tachoA;
	}
	
	/**
	 * to get the light value from the robot
	 * @return the light value
	 */
	public int getLightValue(){
		return lightValue;
	}
	
	/**
	 * to return the battery level
	 * @return the battery level
	 */
	public int getBattery(){
		return battery;
	}
}