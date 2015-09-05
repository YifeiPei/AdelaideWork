package BasicMove;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * The class is used to control the robot to follow the road
 * @fileName RoadFollower
 * @author Yu Hong
 *
 */
public class RoadFollower extends Thread{
	LightSensor light;
	DataExchange deOBJ;
	boolean status;
	//light.setFloodlight(true);

	private final int power = 40;
	private final int noPower = 0;

	/**
	 *  the constructor of the class
	 * @param de the data object to be exchanged
	 */
	public RoadFollower(DataExchange de){
		deOBJ = de;
		light = new LightSensor(SensorPort.S1);
		light.setFloodlight(true);
		Motor.A.setSpeed(90);
		Motor.B.setSpeed(90);
	}
	
	/**
	 * the thread method to control the robot to follow the road
	 */
	public void run(){
		while (true){
			status = deOBJ.getDangerStatus();

			if (status == false){
				 LCD.drawInt(SensorPort.S1.readValue(), 4, 0, 3);
				if (light.readValue() < 45){
					MotorPort.B.controlMotor(power, BasicMotorPort.FORWARD);
					MotorPort.A.controlMotor(noPower, BasicMotorPort.STOP);
					LCD.refresh();

				}
				if ((light.readValue() <=46)&&(light.readValue()>=44)){
					MotorPort.B.controlMotor(30, BasicMotorPort.FORWARD);
					MotorPort.A.controlMotor(30, BasicMotorPort.FORWARD);
					LCD.refresh();

				}
				if (light.readValue() > 47){
					MotorPort.A.controlMotor(power, BasicMotorPort.FORWARD);
					MotorPort.B.controlMotor(noPower, BasicMotorPort.STOP);
					LCD.refresh();
				}
			}else{
				MotorPort.A.controlMotor(noPower, BasicMotorPort.STOP);
				MotorPort.B.controlMotor(noPower, BasicMotorPort.STOP);
			}
		}
	}
}
