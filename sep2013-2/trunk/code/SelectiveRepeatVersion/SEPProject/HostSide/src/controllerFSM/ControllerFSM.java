package controllerFSM;

import java.io.IOException;

import gui.*;
import hostSideCommsUnit.*;
import mapDataStructure.*;
import artificialIntelligenceUnit.*;

/**
 * @author Matthew Nestor
 * @filename ControllerFSM.java
 * @package controllerFSM
 * @project HostSide
 * @date 16/10/2013
 */

public class ControllerFSM {

	private GuiOfSEP gui;
	private Map map;
	private PCComms communicator;
	private ArtificialIntelligenceFSM AI;
	private int state;
	private static final int MANUAL = 0;
	private static final int AUTOMATIC = 1;

	public ControllerFSM(){
		state = MANUAL;
	}

	public void setGUI(GuiOfSEP gui){
		this.gui = gui;
	}

	public void setMap(Map m){
		map = m;
		AI = new ArtificialIntelligenceFSM(map, this);
	}
	
	public Graph getGraph(){
		return AI.getGraph();
	}

	
	public PCComms createPCComms(){
		communicator = new PCComms(this, map);
		AI.setPCComms(communicator);
		return communicator;
	}

	public void setAI(ArtificialIntelligenceFSM aifsm){
		AI = aifsm;
	}
	
	public PCComms getPCComms(){
		return communicator;
	}
	
	public void AIFoundNext(){
		AI.foundNext();
	}

	public void stopPressed() throws InterruptedException{
		state = MANUAL;
		if(AI.isAlive()){
			AI.terminateThread();
			AI.join();
		}
		gui.transferToGUI("AI mode stopped!");
	}

	public void connectionFailure() throws InterruptedException{
		switch(state){
		case AUTOMATIC:
			state = MANUAL;
			if(AI.isAlive()){
				AI.terminateThread();
				AI.join();
			}
			gui.transferToGUI("Connection failure...");

		case MANUAL:
			gui.transferToGUI("Connection failure...");
		}
	}

	public void startAutomaticMapping(){
		state = AUTOMATIC;
		AI.start();
	}

	public void exceptionThrown(Exception e) throws InterruptedException{
		state = MANUAL;
		if(AI.isAlive()){
			AI.terminateThread();
			AI.join();
		}
		gui.transferToGUI("Exception thrown: "+e.getMessage());
	}
}
