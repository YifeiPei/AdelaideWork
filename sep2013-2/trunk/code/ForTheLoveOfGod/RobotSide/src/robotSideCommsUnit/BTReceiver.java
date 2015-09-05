package robotSideCommsUnit;

import java.io.DataOutputStream;
import java.io.IOException;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

/**
 * This class consists of the main method used to initialize the
 * communication between PC and robot.
 * @fileName BTReceiver
 * @author Yu Hong
 *
 */
public class BTReceiver {
	/**
	 * The main method used to initialize the communciation.
	 * @param args the default arguments
	 * @throws Exception
	 */
  public static void main(String [] args){
	  /*
	  LCPBTResponder lcpThread = new LCPBTResponder();
	  lcpThread.setDaemon(true);
	  lcpThread.start();
	  */
	  RobotCommunication rc = new RobotCommunication();
	  rc.start();
	  
  }
}
