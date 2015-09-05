package BasicMove;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * @filename PilotMove
 * @author Yu Hong
 *
 */
 
public class PilotMove {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 DifferentialPilot pilot = new DifferentialPilot(5.6f, 11.1, Motor.A, Motor.B, false);  // parameters in inches
		 // set travel speed;
		 pilot.setTravelSpeed(15);
		 pilot.travel(50);  // move forward 50cm;
		 pilot.travel(-50); // move backward 50cm;
		 pilot.setRotateSpeed(60);  // set rotate speed;
		 pilot.rotate(-90); // rotate 90 degree clockwise;
		 Delay.msDelay(100);
		 pilot.rotate(90); // rotate back;
		 Delay.msDelay(1000);
		 pilot.rotate(90); // rotate 90 degree anticlockwise;
		 Delay.msDelay(100);
		 pilot.rotate(-90); //rotate back;
		 pilot.stop(); // stop;
	}

}
