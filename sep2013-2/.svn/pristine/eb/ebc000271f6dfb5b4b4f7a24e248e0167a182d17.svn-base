package BasicMove;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/**
 * the class would detect whether the robot is bumping into
 * an obstacle
 * @fileName BumpDetector
 * @author Yu Hong
 *
 */
public class BumpDetector extends Thread {
	DataExchange deOBJ;
	TouchSensor leftTouch;
	TouchSensor rightTouch;
	boolean status;
	
	/**
	 * the constructor of the class
	 * @param de the data object to be exchanged
	 */
	public BumpDetector(DataExchange de){
		deOBJ = de;
		leftTouch = new TouchSensor(SensorPort.S2);
		rightTouch = new TouchSensor(SensorPort.S3);
		status = false;
	}
	
	/**
	 * the thread method to get the value of bump sensors
	 */
	public void run(){
		while (true){
			if ((leftTouch.isPressed() || rightTouch.isPressed())== true){
				status = true;
				deOBJ.setDangerStatus(status);
				LCD.drawString("Bumpped Into Obstacle", 2, 2);
				LCD.refresh();
			}else{
				LCD.drawString(" ", 2, 2);
			}
		}
	}
}
