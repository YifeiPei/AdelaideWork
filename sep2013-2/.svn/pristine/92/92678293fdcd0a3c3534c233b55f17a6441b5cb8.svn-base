package BasicMove;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.util.Delay;

public class MotorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        LCD.drawString("Program 2", 0, 0);
           Button.waitForAnyPress();
           Motor.A.setSpeed(360);
           Motor.B.setSpeed(360);
           Motor.A.forward();
           Motor.B.forward();
           LCD.clear();
           Delay.msDelay(2900);
           LCD.drawInt(Motor.A.getTachoCount(),0,0);
           Motor.A.stop(true);
           Motor.B.stop();
           
           Motor.A.backward();
           Motor.B.backward();
           Delay.msDelay(2900);
           Motor.B.stop(true);
           Motor.A.stop();

           Motor.A.rotate(360);
           Motor.B.backward();
           Motor.B.rotate(360);
           Motor.A.backward();
//           Motor.A.rotateTo(0);
//           Motor.B.rotateTo(0);
           Button.waitForAnyPress();   
}

}
