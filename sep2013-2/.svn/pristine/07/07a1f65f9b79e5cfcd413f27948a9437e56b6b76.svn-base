package gui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import hostSideCommsUnit.PCReceiver;
import hostSideCommsUnit.PCComms;
import lejos.pc.comm.NXTCommException;

/**
 * 
 * The class consists of the commands to be sent to the 
 * robot side and the connetion methods.
 * @fileName GUICommands
 * @author Yu Hong
 *
 */
public class GUICommands {
	PCReceiver receiver;
	PCComms communication;
	Timer t1;
	Timer t2;
	boolean connectionStatus;
	private int message;

	private final int STOP = 0;
	private final int FORWARD = 1;
	private final int BACKWARD = 2;
	private final int TURNLEFT = 3;
	private final int TURNRIGHT = 4;
	private final int BATTERYINFO = 11;
	private final int LIGHTINFO = 12;
	private final int TACHOINFO_A = 13;
	private final int TACHOINFO_B = 14;
	private final int RANGEINFO = 15;
	private final int GETANGEL = 16;
	private final int GETDISTANCE = 17;
	
	private final int MARK = 20;
	private final int DISCONNECTION = -1;
	private int selectedSpeed = 0;

	/**
	 * the constructor of the class, which create
	 *  the timer for communication
	 */
	public GUICommands(){}
	
	public void getReceiver(PCReceiver pcr){
		receiver = pcr;
	}

	/**
	 * to establish the connection between the PC and Robot.
	 * @return a boolean value that indicates whether it is
	 * connected or not
	 * @throws NXTCommException
	 * @throws IOException
	 */
	public boolean connect() throws NXTCommException, IOException{
		communication = new PCComms("Robot", receiver);
		connectionStatus = communication.isconnected();
		if (connectionStatus){
			t1 = new Timer();
			t2 = new Timer();
			t1.schedule(new SensorRequestTask(), 0, 1000);
			t2.schedule(new BatteryInfoTask(), 0, 30*1000);
			this.receiveMessage();
		}
		return connectionStatus;
	}

	/**
	 * to disconnect the connection between the PC and Robot.
	 * @throws IOException
	 */
	public void disconnect() throws IOException{
		this.sendDisconnection();
		communication.disconnection();
		t1.cancel();
		t1.purge();
		t2.cancel();
		t2.purge();
	}

	/**
	 * send the command of disconnection to the Robot.
	 * @throws IOException
	 */
	private void sendDisconnection() throws IOException{
		communication.sendMessage(DISCONNECTION);
	}

	/**
	 * send the command of forward to the robot.
	 * @throws IOException
	 */
	public void forward() throws IOException{
		communication.sendMessage(FORWARD);
	}

	/**
	 * send the command of backward to the robot.
	 * @throws IOException
	 */
	public void backward() throws IOException{
		communication.sendMessage(BACKWARD);
	}

	/**
	 * send the command of turnLeft to the robot.
	 * @throws IOException
	 */
	public void turnLeft() throws IOException{
		communication.sendMessage(TURNLEFT);
	}

	/**
	 * send the command of turnRight to the robot.
	 * @throws IOException
	 */
	public void turnRight()throws IOException{
		communication.sendMessage(TURNRIGHT);
	}

	/**
	 * send the command of emgerencyStop to the robot.
	 * @throws IOException
	 */
	public void emgerencyStop() throws IOException{
		communication.sendMessage(STOP);
	}
	
	/**
	 * to mark the road closure on the real map
	 * @throws IOException 
	 */
	public void markClosure() throws IOException {
		// TODO Auto-generated method stub
		communication.sendMessage(MARK);
	}	
	
	/**
	 * set the movement speed of the robot
	 * @param speed the selected speed
	 * @throws IOException
	 */
	public void setSpeed(int speed) throws IOException{
		selectedSpeed = speed+100;
		communication.sendMessage(selectedSpeed);
	}

	/**
	 * to initialise the thread of receiving message
	 * from the robot
	 * @throws IOException
	 */
	public void receiveMessage() throws IOException{
		communication.start();
	}

	/**
	 * to get the light value of the sensor
	 * @return the light value
	 */
	public int getLightValue(){
		return receiver.getLightValue();
	}

	/**
	 * to get the battery level of the Robot
	 * @return the battery level
	 */
	public int getBatteryLevel(){
		return receiver.getBattery();
	}
	
	/**
	 * to get the range sensor value of the Robot
	 * @return the range sensor value
	 */
	public int getDistance(){
		return receiver.getDistance();
	}
	/**
	 *	to send the sensor request to the robot 
	 *	according to the timer
	 *
	 */
	class SensorRequestTask extends TimerTask{

		@Override
		/**
		 * to send to light and tacho sensor request to the robot
		 */
		public void run() {
			// TODO Auto-generated method stub
			try {
				communication.sendMessage(LIGHTINFO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//communication.sendMessage(GETANGEL);
				communication.sendMessage(GETDISTANCE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				communication.sendMessage(RANGEINFO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 *	to send the battery level request to the robot 
	 *	according to the timer
	 *
	 */
	class BatteryInfoTask extends TimerTask{

		@Override
		/**
		 * to send the battery level request to the robot
		 */
		public void run() {
			// TODO Auto-generated method stub
			try {
				communication.sendMessage(BATTERYINFO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

}