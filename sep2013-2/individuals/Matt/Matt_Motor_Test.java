	import lejos.nxt.Button;
	import lejos.nxt.LCD;
	import lejos.nxt.Motor;
	import lejos.util.Delay;

	public class Matt_Motor_Test {

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	        LCD.drawString("Matt's Test 1 ...", 0, 0);
	           Button.waitForAnyPress();
	           //move forward 50cm
	           Motor.A.setSpeed(360);
	           Motor.B.setSpeed(360);
	           Motor.A.forward();
	           Motor.B.forward();
	           Delay.msDelay(2900);
	           LCD.drawInt(Motor.A.getTachoCount(),0,1);
	           Motor.A.stop(true);
	           Motor.B.stop();
	           Delay.msDelay(100);
	           
	           //move backward 50cm
	           Motor.A.backward();
	           Motor.B.backward();
	           Delay.msDelay(2900);
	           LCD.drawInt(Motor.A.getTachoCount(),0,2);
	           Motor.B.stop(true);
	           Motor.A.stop();
	           Delay.msDelay(100);

	           //Turn left 90 degrees
	           Motor.A.setSpeed(180);
	           Motor.B.setSpeed(180);
	           Motor.A.rotate(180,true);
	           Motor.B.rotate(-180);
	           Delay.msDelay(100);
	           Motor.B.rotate(180,true);
	           Motor.A.rotate(-180);
	           Delay.msDelay(100);
	           
	           //Turn right 90 degrees
	           Motor.B.rotate(180,true);
	           Motor.A.rotate(-180);
	           Delay.msDelay(100);
	           Motor.A.rotate(180,true);
	           Motor.B.rotate(-180);
	           Delay.msDelay(100);
	           
	           //Turn head left
	           Motor.C.setSpeed(180);
	           Motor.C.rotate(140);
	           Delay.msDelay(100);
	           
	           /*Write
	           Motor.B.rotate(70,true);
	           Motor.A.rotate(-70);
	           Motor.A.rotate(140,true);
	           Motor.B.rotate(-140);
	           Motor.B.rotate(70,true);
	           Motor.A.rotate(-70);
	           Delay.msDelay(100);
	           */
	           
	           //Raise arm
	           Motor.C.rotate(-280);
	           Motor.C.rotate(140);
	           Button.waitForAnyPress();
	}

}
