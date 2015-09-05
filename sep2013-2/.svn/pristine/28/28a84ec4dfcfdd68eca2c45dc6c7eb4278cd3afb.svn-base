package BasicMove;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * the class would detect the distance towards an obstacle
 * @fileName ObstacleDetector
 * @author Yu Hong
 *
 */
public class ObstacleDetector extends Thread {
	UltrasonicSensor range;
	DataExchange deOBJ;
	boolean status;
	private int obstacleDistance;
	
	/**
	 * the constructor of the class
	 * @param de the data object to be exchanged
	 * @param dis the security distance
	 */
	public ObstacleDetector(DataExchange de, int dis){
		deOBJ = de;
		range = new UltrasonicSensor(SensorPort.S4);
		status = false;
		obstacleDistance = dis;
	}
	
	/**
	 * the thread method used to detect whether the robot 
	 * is approaching an obstacle 
	 */
	public void run(){
		//LCD.drawInt(123, 3, 1, 0);
		while(true){
			if (range.getDistance() > obstacleDistance){
				status = false;
				deOBJ.setDangerStatus(status);
				LCD.drawString("", 0, 1);
			}else{
				status = true;
				deOBJ.setDangerStatus(status);
				LCD.drawString("Obstacle Found", 0, 1);
			}
		}
	}
}
