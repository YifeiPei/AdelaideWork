package HostSide.controllerFSM;

import javax.swing.SwingUtilities;

import HostSide.gui.*;
import HostSide.hostSideCommsUnit.*;
import HostSide.mapDataStructure.*;
import HostSide.artificialIntelligenceUnit.*;

/**
 * @filename Main
 * @author Matthew Nestor
 *
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
