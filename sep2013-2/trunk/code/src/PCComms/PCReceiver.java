
/**
 * The class is used to handle the message sent from the robot.
 * @author Yu Hong
 *
 */
public class PCReceiver {

	private int receiveMessage;
	private int lightValue;
	private int battery;
	private int tachoA;
	private int tachoB;
	private int distance;
	
	/**
	 * the constructor of the class
	 */
	public PCReceiver(){
		battery = 0;
	}
	
	/**
	 * the method is used to handle the message sent from the robot side.
	 * @param rm the concrete message from the robot and it is represented
	 * as integers.
	 */
	public void messageHandler(int rm){
		receiveMessage = rm;
	
		if (receiveMessage<200 && receiveMessage > 100){
			lightValue = receiveMessage - 100;
			// System.out.println("lightValue: "+ lightValue);
		}
		if (receiveMessage<300 && receiveMessage >= 200){
			battery = receiveMessage - 200;
		}
		if (receiveMessage<400 && receiveMessage >= 300){
			distance = receiveMessage -300;
		}
		
		if (receiveMessage<0){
			tachoA = receiveMessage + 1000;
			System.out.println("tacho: "+ tachoA);
		}
		if (receiveMessage>1000){
			tachoA = receiveMessage - 1000;
			System.out.println("tacho: "+ tachoA);
		}
	}
	
	/**
	 * to get the distance between the robot and the obstacle
	 * @return the distance
	 */
	public int getDistance(){
		return distance;
	}
	
	/**
	 * to get the tacho value from the robot
	 * @return tacho value
	 */
	public int getTachoValue(){
		return tachoA;
	}
	
	/**
	 * to get the light value from the robot
	 * @return the light value
	 */
	public int getLightValue(){
		return lightValue;
	}
	
	/**
	 * to return the battery level
	 * @return the battery level
	 */
	public int getBattery(){
		return battery;
	}
}
