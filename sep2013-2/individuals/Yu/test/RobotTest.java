package hostSideCommsUnit;

import static org.junit.Assert.*;

import java.io.IOException;

import gui.GUICommands;
import junit.framework.Assert;

import lejos.pc.comm.NXTCommException;
import mapDataStructure.RobotMap;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Yu Hong
 * @fileName RobotTest
 *
 */
public class RobotTest {
	PCComms communication = new PCComms("Robot");
	
	@Test
	public void testConnection() throws  NXTCommException, IOException {
		boolean connected = communication.isconnected();
		Assert.assertEquals(true, connected);
	}
	
	@Test
	public void testDisconnection() throws  NXTCommException, IOException {
		boolean connected = communication.isconnected();
		connected = communication.disconnection();
		Assert.assertEquals(true, connected);
	}
	@Test
	public void testForward() throws IOException, NXTCommException {
	
		communication.isconnected();
		communication.sendMessage(1);
		int command =communication.dis.readInt();
		Assert.assertEquals(1,command);
	}
	@Test
	public void testSensorCommand() throws IOException, NXTCommException {
	
		communication.isconnected();
		communication.sendMessage(11);
		int command =communication.dis.readInt();
		Assert.assertEquals(11,command);
	}
	@Test
	public void testReturnValues() throws IOException, NXTCommException {
	
		communication.isconnected();
		communication.sendMessage(100000);
		int command =communication.dis.readInt();
		Assert.assertEquals(100000,command);
	}

}
