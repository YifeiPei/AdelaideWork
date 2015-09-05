package hostSideCommsUnit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.Timer;

import lejos.nxt.LCD;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

/**
 * this class is used to directly communicate with the robot.
 * @fileName PCComms
 * @author Yu Hong
 *
 */
public class PCComms extends Thread implements Comms {
	PCReceiver receiver;
	NXTComm nxtComm;
	NXTInfo[] nxtInfo;
	 NXTConnector connector;
	static String name = null;
	DataInputStream dis;
	DataOutputStream dos;
	private int getInfo;
	private int sendInfo;
	private int b;
	private volatile boolean finished = false;
	private Timer timer;
	private ActionListener task;

	/**
	 * constructor of the class
	 * @param robotName the configured name of the robot
	 */
	public PCComms(String robotName, PCReceiver receive){
		this.name = robotName;
		receiver = receive;
		task = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          finished = false;
		          disconnection();
		          try {
					receiver.connectionFailure();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		  };
		timer  = new Timer(5000, task);
	}

	/**
	 * to establish the connection between the PC and Robot directly.
	 * @return a boolean value that indicates whether the connection
	 * is established or not.
	 * @throws NXTCommException
	 */
	public boolean isconnected() throws NXTCommException{
		 connector = new NXTConnector();
		 boolean connected = connector.connectTo("btspp://");
		 if (connected == false){
			System.err.println("Failed to connect to any NXT");
			return connected;
			}
		 else{
			System.err.println("succeed in connecting to any NXT");
			dis = new DataInputStream(connector.getInputStream());
			dos = new DataOutputStream(connector.getOutputStream());
		 }
			return connected;
	}

	/**
	 * to send the command to the robot
	 * @param command the concrete command represented by integers
	 * @throws IOException
	 */
	public void sendMessage(int command) throws IOException{
		sendInfo = command;
		dos.writeInt((sendInfo));
		dos.flush();
		//System.out.println("Sending: "+ command);
	}
	
	
	/**
	 * the thread method used to receive the message from Robot.
	 */
	public void run(){
		timer.start();
		while (finished == false){
			try {
				while ((b = dis.readInt()) != -1){
					timer.restart();
					getInfo = b;
					receiver.messageHandler(getInfo);
				  System.out.println("receiving: "+ getInfo);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * the method is used to stop the thread.
	 */
	public void stopThread(){
		finished = true;
		timer.stop();
	}

	/**
	 * the method is used to cancel the connection
	 */
	public void disconnection(){
		try {
			
			dis.close();
			dos.close();
			this.stopThread();
			connector.close();
		} catch (IOException ioe) {
			System.out.println("IOException closing connection:");
			System.out.println(ioe.getMessage());
		}
	}
}
