package HostSide.controllerFSM;

import HostSide.gui.*;
import HostSide.hostSideCommsUnit.*;
import HostSide.mapDataStructure.*;
import HostSide.artificialIntelligenceUnit.*;

/**
 * @filename ControllerFSM
 * @author Matthew Nestor
 *
 */

public class ControllerFSM {

	private GuiOfSEP gui;
	private Map map;
	private PCReceiver receiver;

	public ControllerFSM(){}
	
	public void setGUI(GuiOfSEP gui){
		this.gui = gui;
	}
	
	public void setMap(Map m){
		map = m;
		receiver.setMap(m);
	}
	
	public void setReceiver(PCReceiver pcr){
		receiver = pcr;
	}
	
}
