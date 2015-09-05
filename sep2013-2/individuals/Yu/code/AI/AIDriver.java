package BasicMove;

import lejos.nxt.Button;
import lejos.nxt.LCD;

/**
 * This class is used to start the AI mode.
 * @fileName AIDriver
 * @author Yu Hong
 *
 */
public class AIDriver {

	private static DataExchange de;
	private static ObstacleDetector od;
	private static BumpDetector bd;
	private static 	RoadFollower rf;
	private static final int securityDistance = 20;
	
	
	/**
	 * the main method of the class
	 * @param args default arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		de = new DataExchange();
	//	de.setDangerStatus(false);
	bd = new BumpDetector(de);
	od = new ObstacleDetector(de, securityDistance);
	rf = new RoadFollower(de);
	od.start();
	rf.start();
	bd.start();
	while (!Button.ESCAPE.isPressed()){
		
	}
	LCD.drawString("Finished", 0, 7);
	LCD.refresh();
	System.exit(0);
		
	}

}
