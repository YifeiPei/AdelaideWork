

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

/**
 * This class provides the concrete methods responsible for establishing
 * connection, canceling connection, receiving commands and sending messages.
 * @fileName RobotCommunication
 * @author Yu Hong
 *
 */
public class RobotCommunication {
	private int rValue;
	private int command;
	private int message;
	private String ms;
	private int tempCommand = -1;
	private String connected = "Connected";
	private String waiting = "Waiting...";
	private String closing = "Closing...";
	NXTConnection connection;
	RobotBehaviorControl robotBehave;

	DataInputStream dis;
	DataOutputStream dos;

	/**
	 * start the connection from the robot side when a connection 
	 * request from the PC side arrives.
	 */
	public void waitingForConnection(){
		LCD.drawString(waiting,0,0);
		connection = Bluetooth.waitForConnection();
		LCD.clear();
		LCD.drawString(connected,0,0);
		this.createStream();
	}

	/**
	 * create the input and output stream for communication.
	 */
	public void createStream(){
		dis = connection.openDataInputStream();
		dos = connection.openDataOutputStream();
	}

	/**
	 * receive the concrete commands which are represented as integers,
	 *  and pass the commands to the RobotBehaviorControl class.
	 * @throws IOException
	 */
	public void startCommunication() throws IOException{
		robotBehave = new RobotBehaviorControl();
		while ((tempCommand = dis.readInt()) != -1){
			command = tempCommand;
			rValue = robotBehave.setCommand(tempCommand);
			if (rValue != 0){
				this.sendMessage(rValue);
			}
		}
	}

	/**
	 * to send the message to the PC side.
	 * @param m the message to be sent
	 * @throws IOException
	 */
	public void sendMessage(int m) throws IOException{
		this.message = m;
		dos.writeInt(message);
		dos.flush();
	}
	
	/**
	 * the method used to cancel the connection
	 * @throws IOException
	 */
	public void disconnection() throws IOException{
		dis.close();
		dos.close();

		LCD.clear();
		LCD.drawString(closing,0,0);

		connection.close();
		LCD.clear();
	}

}
