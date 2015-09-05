package robotMotorSensorControl;

import java.io.IOException;

import robotSideCommsUnit.RobotCommunication;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * @author Matthew Nestor
 * @filename LineFollower.java
 * @package robotMotorSensorControl
 * @project RobotSide
 * @date 25/10/2013
 */

public class LineFollower extends Thread {
	private RobotCommunication robotComms;
	private DifferentialPilot pilot;
	private LightSensor lightSensor = new LightSensor(SensorPort.S1);
	private OdometryPoseProvider odoPose;
	//line follower data
	private int blackVal; // light sensor black value
	private int whiteVal; // light sensor white value
	private int midVal = (blackVal + whiteVal)/2; // light sensor mid value
	private int error; // difference between current sensor value and midVal
	private double turnRate; // rate of turning
	private int lightSensorRange; //range of values light sensor can take
	private final int steeringCoefficient = 2; //the coefficient that determines
	//how fast the robot turns relative to the error
	private double normalisedError; // error as a percentage of the lightSensorRange
	private int [] scan = new int[200]; // line-scanner data-structure
	private int index;
	private int width;
	private volatile boolean reachedVertex = false;
	private float distanceToTravel;
	private float distanceTravelled;
	private String orientation;
	private float xLast, xNext, yLast, yNext;

	public LineFollower(DifferentialPilot df, RobotCommunication rc, OdometryPoseProvider opp){
		blackVal = 40;
		whiteVal = 52;
		midVal = (blackVal + whiteVal)/2;
		error = 0;
		lightSensorRange = whiteVal - blackVal;
		pilot = df;
		robotComms = rc;
		odoPose = opp;
		distanceTravelled = 0;
	}

	public void setDistance(int distance){
		distanceToTravel = distance;
	}

	public void terminate(){
		reachedVertex = false;
	}

	public void run(){
		//initialise scan data-structure
		for(int i = 0; i < scan.length; i++){
			scan[i] = 0;
		}
		reachedVertex = (distanceToTravel == 0);
		float heading = odoPose.getPose().getHeading();
		if((heading > 135 && heading < 45) || (heading > 225 && heading < 315))
			orientation = "VERTICAL";
		else
			orientation = "HORIZONTAL";
		xLast = odoPose.getPose().getX();
		yLast = odoPose.getPose().getY();
		width = 0;
		pilot.rotate(3);
		pilot.travel(2);
		pilot.forward(); //go forward

		while(!reachedVertex){
			//LCD.clear();
			//LCD.drawString("LineFollower", 0, 0);
			//LCD.drawString("loop: "+String.valueOf(counter), 0, 6);
			error = midVal - lightSensor.readValue();
			normalisedError = (Integer.valueOf(error).doubleValue()/(lightSensorRange/2))*100;
			turnRate = normalisedError * steeringCoefficient;
			pilot.steer(turnRate); //steer proportionately to the error
			//statusMonitor.makeTravelReading();
			xNext = odoPose.getPose().getX();
			yNext = odoPose.getPose().getY();
			if(orientation.equals("HORIZONTAL")){
				distanceTravelled = xNext - xLast;
			}else if(orientation.equals("VERTICAL")){
				distanceTravelled = yNext - yLast;
			}
			distanceToTravel = distanceToTravel - distanceTravelled;
			LCD.drawString("Total = "+String.valueOf(distanceTravelled), 0, 0);
			LCD.drawString("Remaining = "+distanceToTravel, 0, 1);
			xLast = xNext;
			yLast = yNext;

			if((lightSensor.readValue() < blackVal) || (lightSensor.readValue() == whiteVal)){
				//scan line to test if it is an intersection
				pilot.rotate(20);
				pilot.setRotateSpeed(20); // rotate speed = 20deg/sec = 20deg/1000ms, so ...
				pilot.rotate(-40, true);
				index = 0;
				while(pilot.isMoving()){
					if(index < scan.length){
						scan[index] = lightSensor.readValue();
						LCD.clear();
						LCD.drawInt(scan[index], 0, 0);
						index++;
						Delay.msDelay(50); //... read one value every 50ms (20/1000=50)
					}
					else
						break;
				}
				pilot.rotate(20);
				//width will determine: line or intersection?
				width = 0;
				for(int i = 0; i < scan.length; i++){
					if(scan[i] < midVal){
						width++;
					}
				}
				LCD.clear();
				LCD.drawInt(width, 0, 0);
				Delay.msDelay(5000);

				//reset scan
				for(int i = 0; i < scan.length; i++){
					scan[i] = 0;
				}

				if(width >= 170){
					//then it is an intersection, so exit
					break;
				}else{
					pilot.rotate(100);
					pilot.setRotateSpeed(20); // rotate speed = 20deg/sec = 20deg/1000ms, so ...
					pilot.rotate(-200, true);
					index = 0;
					while(pilot.isMoving()){
						if(index < scan.length){
							scan[index] = lightSensor.readValue();
							LCD.drawInt(scan[index], 0, 0);
							index++;
							Delay.msDelay(50); //... read one value every 50ms (20/1000=50)
						}
						else
							break;
					}
					//robot got lost, so try to find the road again and rotate to it
					index = 0;
					//get the start of the road
					for(int i = 0; i < scan.length; i++){
						if(scan[i] <= midVal){
							index = i;
							break;
						}
					}
					// ... rotate to it
					LCD.clear();
					LCD.drawInt(index, 0, 0);
					pilot.rotate(scan.length - index);
				}
			}

			if(distanceToTravel <= 0){
				break;
			}
		}
		try {
			robotComms.reachedVertex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
