import java.io.IOException;
import java.util.ArrayList;

import RobotBehaviors.Forward;
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
public class RobotBehaviorControl  {
	RobotCommunication robotComms;
	DifferentialPilot pilot;
	private int command;
	ArrayList<Integer> commandList = new ArrayList<Integer>();
	SensorInfo sensor;
	UltrasonicSensor range;
	private int light_Value;
	private int distance;
	private int tachoA;
	private int tachoB;
	
	/**
	 * the constructor of the class
	 */
	public RobotBehaviorControl(){
		pilot = new DifferentialPilot(5.6f, 13.5, Motor.A, Motor.B, false);  // parameters in inches
		pilot.setTravelSpeed(15);
		pilot.setRotateSpeed(60);
		sensor = new SensorInfo();
		robotComms = new RobotCommunication();
		range = new UltrasonicSensor(SensorPort.S4);
	}
	
	/**
	 * to set the current command and store it to the arraylist
	 * @param c the concrete command represented as integer
	 * @return the result value
	 * @throws IOException
	 */
	public int setCommand(int c) throws IOException{
		command = c;
		commandList.add(c);
		return this.doCommand();
	}
	
	/**
	 * the method would control the robot to perform certain tasks
	 * according to the command received.
	 * @return the result value
	 * @throws IOException
	 */
	public int doCommand() throws IOException{
		int size = commandList.size();
		if (command == -1){
			robotComms.disconnection();
		}
		if (command == 0){
			pilot.stop();
		}
		if (command == 1){
			pilot.forward();
		}
		if (command == 2){
			pilot.backward();
		}
		if (command == 3){
			pilot.rotate(90);
			
			if (commandList.get(size-2) == 1){
				pilot.forward();
				this.setCommand(1);
			}
			if (commandList.get(size-2) == 2){
				pilot.backward();
				this.setCommand(2);
			}
		}
		if (command == 4){
			pilot.rotate(-90);
			if (commandList.get(size-2) == 1){
				pilot.forward();
				this.setCommand(1);
			}
			if (commandList.get(size-2) == 2){
				pilot.backward();
				this.setCommand(2);
			}
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
		return 0;
	}
		
}