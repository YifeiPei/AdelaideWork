package RobotSide.robotMotorSensorControl;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

/**
 * The class is used to obtain the values of sensors
 * @fileName SensorInfo
 * @author Yu Hong
 *
 */

public class SensorInfo {
	LightSensor light;
	UltrasonicSensor range;
	TouchSensor leftTouch;
	TouchSensor rightTouch;
	private int lightValue;
	private int rangeValue;
	
	/**
	 * the constructor of the claSS
	 */
	public SensorInfo(){
		light = new LightSensor(SensorPort.S1);
		range = new UltrasonicSensor(SensorPort.S4);
		leftTouch = new TouchSensor(SensorPort.S2);
		rightTouch = new TouchSensor(SensorPort.S3);
	}
	
	/**
	 * to get the value of light sensor
	 * @return value of light sensor
	 */
	public int getLightValue(){
		lightValue = light.readValue();
		return lightValue;
	}
	
	/**
	 * to get the value of range sensor
	 * @return the value of range sensor
	 */
	public int getRangeValue(){
		rangeValue = range.getDistance();
		return rangeValue;
	}
	
	/**
	 * to get the value of touch sensor
	 * @return a boolean indicating whether the robot is 
	 * bumping into an obstacle or not
	 */
	public boolean isBumped(){
		return (leftTouch.isPressed() || rightTouch.isPressed());
	}
}
