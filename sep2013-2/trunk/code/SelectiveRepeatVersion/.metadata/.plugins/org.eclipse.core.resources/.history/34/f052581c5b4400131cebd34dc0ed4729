package controllerFSM;

import javax.swing.SwingUtilities;

import gui.*;
import hostSideCommsUnit.*;
import mapDataStructure.*;
import artificialIntelligenceUnit.*;

/**
 * @author Matthew Nestor
 * @filename Main.java
 * @package controllerFSM
 * @project HostSide
 * @date 16/10/2013
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(SwingUtilities.isEventDispatchThread());
		GuiOfSEP gui = new GuiOfSEP();		
		PCReceiver receiver = new PCReceiver();
		ControllerFSM controllerFSM = new ControllerFSM();
		gui.setReceiver(receiver);
		gui.setFSM(controllerFSM);
		controllerFSM.setGUI(gui);		
		controllerFSM.setReceiver(receiver);		
		receiver.setFSM(controllerFSM);
	}

}
