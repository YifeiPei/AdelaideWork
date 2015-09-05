import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class GUICommandsTest {

	@Test
	public void testStop() {
		
		GUICommands t1= new GUICommands();
		int stop = t1.stop();
		Assert.assertEquals(0,stop);
	}

	@Test
	public void testForward() {
		GUICommands t2= new GUICommands();
		int forward = t2.forward();
		Assert.assertEquals(1,forward);
	}

	@Test
	public void testBackward() {
		GUICommands t3= new GUICommands();
		int backward = t3.backward();
		Assert.assertEquals(2,backward);
	}

	@Test
	public void testTurnleft() {
		GUICommands t4= new GUICommands();
		int left = t4.turnleft();
		Assert.assertEquals(3,left);
	}

	@Test
	public void testTurnright() {
		GUICommands t5= new GUICommands();
		int right = t5.turnright();
		Assert.assertEquals(4,right);
	}

	@Test
	public void testrun() {
		GUICommands t6= new GUICommands();
		int batteryinfo = t6.run();
		Assert.assertEquals(12,batteryinfo);
	}

	@Test
	public void testmarkClosure() {
		
		GUICommands t7= new GUICommands();
		int mark = t7.markClosure();
		Assert.assertEquals(20,mark);
	}

	@Test
	public void testDisconncet() {
		GUICommands t8= new GUICommands();
		int disconnect = t8.disconncet();
		Assert.assertEquals(-1,disconnect);
	}

}
