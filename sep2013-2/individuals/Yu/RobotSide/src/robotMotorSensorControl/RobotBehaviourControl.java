package robotMotorSensorControl;

import java.io.IOException;
import java.util.ArrayList;
import robotSideCommsUnit.RobotCommunication;
import lejos.nxt.Battery;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

/**
 * the class contains the methods to control the robot to do 
 * some certain tasks according to the commands received.
 * @fileName RobotBehaviorControl
 * @author Yu Hong
 *
 */

public class RobotBehaviourControl {
	RobotCommunication robotComms;
	DifferentialPilot pilot;
	private int command;
	SensorInfo sensor;
	UltrasonicSensor range;
	private int light_Value;
	private int distance;
	private int tachoA;
	private int tachoB;
	private int speed;
	private int travelAngle;
	private int totalDistance;
	private int incrementDistance;
	
	/**
	 * the constructor of the class
	 */
	public RobotBehaviourControl(){
		pilot = new DifferentialPilot(5.6f, 13.5, Motor.A, Motor.B, false);  // parameters in inches
		pilot.setTravelSpeed(5);
		pilot.setRotateSpeed(60);
		sensor = new SensorInfo();
		robotComms = new RobotCommunication();
		range = new UltrasonicSensor(SensorPort.S4);
		incrementDistance = 0;
		totalDistance = 0;
	}
	
	/**
	 * to set the current command and store it to the arraylist
	 * @param c the concrete command represented as integer
	 * @return the result value
	 * @throws IOException
	 */
	public int setCommand(int c) throws IOException{
		command = c;
		return this.doCommand();
	}
	
	/**
	 * the method would control the robot to perform certain tasks
	 * according to the command received.
	 * @return the result value
	 * @throws IOException
	 */
	public int doCommand() throws IOException{
		if (command == -1){
			robotComms.disconnection();
		}
		if (command == 0){
			incrementDistance = 0;
			totalDistance = 0;
			pilot.stop();
		}
		if (command == 1){
			incrementDistance = 0;
			totalDistance = 0;
			pilot.forward();
		}
		if (command == 2){
			incrementDistance = 0;
			totalDistance = 0;
			pilot.backward();
		}
		if (command == 3){
			incrementDistance = 0;
			totalDistance = 0;
			pilot.rotate(90);
		}
		if (command == 4){
			incrementDistance = 0;
			totalDistance = 0;
			pilot.rotate(-90);
		}
		
		if (command == 11){
			double batteryVoltage =Battery.getVoltageMilliVolt();
			double batteryLevel = (batteryVoltage - 7000)/1400.0;
			batteryLevel = batteryLevel*100;
			int battery = (int)batteryLevel + 200;
			return battery;
		}
		if (command == 12){
			light_Value = sensor.getLightValue()+100;
			return light_Value;
			}
		if (command == 13){
			tachoA = Motor.A.getTachoCount();
	//		Motor.A.resetTachoCount();
			LCD.drawInt(tachoA,2,4);
			if (tachoA >= 0){
				tachoA = tachoA+1000;
				return tachoA;
			}
			if (tachoA < 0){
				tachoA = tachoA-1000;
				return tachoA;
			}
		}
//		if (command == 14){
//			tachoB = Motor.B.getTachoCount();
//			Motor.A.resetTachoCount();
//			LCD.drawInt(tachoB,2,6);
//			tachoB = tachoB+1000;
//			if (tachoB >= 0){
//				tachoB = tachoB+1000;
//				return tachoB;
//			}
//			if (tachoB < 0){
//				tachoB = tachoB-1000;
//				return tachoB;
//			}
//		}
		
		if (command == 15){
			distance = range.getDistance();
			distance = distance + 300;
			return distance;
		}
		if (command == 17){
			incrementDistance = totalDistance;
			totalDistance = (int) pilot.getMovement().getDistanceTraveled();
			incrementDistance = totalDistance- incrementDistance;
			return incrementDistance + 20000;
		}
		if (command == 20){
			pilot.rotate(180);
	        pilot.travel(5);
	        Delay.msDelay(100);
	        Motor.C.setSpeed(30);
	        Motor.C.rotate(-35);
	        pilot.rotate(5);
	        pilot.rotate(-10);
	        pilot.rotate(5);
	        Motor.C.rotate(35);
	        pilot.travel(-5);
	        pilot.rotate(-180);
			incrementDistance = 0;
			totalDistance = 0;
		}
		
		if (command >=100 && command <=115){
			speed = command - 100;
			pilot.setTravelSpeed(speed);
		}
		return 0;
	}
}
