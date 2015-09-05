package BasicMove;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * An example showing how the robot would follow the line
 * @fileName LineFollower
 * @author Yu Hong
 *
 */
public class LineFollower {

	/**
	 * the main method
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int securityDistance = 15;
		int power = 40;
		int noPower = 0;
		LightSensor light = new LightSensor(SensorPort.S1);
		//ss.setFloodlight(true);
		UltrasonicSensor range = new UltrasonicSensor(SensorPort.S4);
		Motor.A.setSpeed(90);
		Motor.B.setSpeed(90);
		while (range.getDistance() > securityDistance){
			LCD.drawInt(range.getDistance(), 3, 1, 0);
			LCD.drawInt(light.readValue(), 3, 2, 0);
			LCD.refresh();
			//LCD.drawInt(range.getDistance(), 3, 9, 0);
			while (light.readValue() < 45){
				MotorPort.B.controlMotor(power, BasicMotorPort.FORWARD);
				MotorPort.A.controlMotor(noPower, BasicMotorPort.STOP);
				
			}
			while (light.readValue() >= 45){
				MotorPort.A.controlMotor(power, BasicMotorPort.FORWARD);
				MotorPort.B.controlMotor(noPower, BasicMotorPort.STOP);
				
			}
		}
	}

}
