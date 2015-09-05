package robotMotorSensorControl;

import lejos.robotics.navigation.DifferentialPilot;

/**
 * @author Matthew Nestor
 * @filename RobotStatusMonitor.java
 * @package robotMotorSensorControl
 * @project RobotSide
 * @date 31/10/2013
 */

public class RobotStatusMonitor {
	private DifferentialPilot pilot;
	private double totalDistanceTravelled;
	private double incrementDistanceTravelled;
	private double distanceToTravel;
	private double newReading;
	private double oldReading;

	public RobotStatusMonitor(DifferentialPilot df){
		pilot = df;
		totalDistanceTravelled = 0;
		incrementDistanceTravelled = 0;
		distanceToTravel = 0;
		newReading = 0;
		oldReading = 0;
	}

	public void makeTravelReading(){
		oldReading = newReading;
		newReading = pilot.getMovement().getDistanceTraveled();
		totalDistanceTravelled = newReading;
		incrementDistanceTravelled = newReading - oldReading;
	}

	public void reset(){
		totalDistanceTravelled = 0;
		incrementDistanceTravelled = 0;
		distanceToTravel = 0;
		newReading = 0;
		oldReading = 0;
	}

	public void setDistanceToTravel(int i){
		distanceToTravel = i;
	}

	public int getDistanceToTravel(){
		return (int)distanceToTravel;
	}

	public int getTotalDistanceTravelled(){
		return (int)totalDistanceTravelled;
	}

	public int getIncrementDistanceTravelled(){
		return (int)incrementDistanceTravelled;
	}
}