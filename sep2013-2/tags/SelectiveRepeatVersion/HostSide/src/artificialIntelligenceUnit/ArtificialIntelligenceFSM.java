package artificialIntelligenceUnit;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Deque;

import mapDataStructure.Map;
import controllerFSM.ControllerFSM;
import hostSideCommsUnit.PCComms;
/**
 * @author Matthew Nestor
 * @filename ArtificialIntelligenceFSM.java
 * @package artificialIntelligenceUnit
 * @project HostSide
 * @date 17/10/2013
 */

public class ArtificialIntelligenceFSM extends Thread{
	private Graph graph;
	private Map map;
	private ControllerFSM controllerFSM;
	private PCComms communications;
	private volatile boolean running;
	private volatile boolean foundNext;

	public ArtificialIntelligenceFSM(Map m, ControllerFSM cfsm){
		map = m;
		graph = new Graph(map);
		controllerFSM = cfsm;
		initialiseGraph();
	}

	public Graph getGraph(){
		return graph;
	}

	public void terminateThread(){
		running = false;
	}

	public void foundNext(){
		foundNext = true;
	}

	public void setPCComms(PCComms pcc){
		communications = pcc;
	}

	public void run(){
		running = true;
		foundNext = false;
		Action nextAction;
		try{
		while(running){
			//get graph instruction stack
			graph.updateGraph();
			Deque<Action> solutionSet = graph.getNextActionSet();
			//check if the robot needs to go anywhere
			//if so, foundAll = false;
			//else foundAll = true;
			while(!solutionSet.isEmpty()){

				//get current orientation of robot
				int orientation = map.getRobot().getRobotOrientation();
				//pop the next action from the instruction stack
				nextAction = solutionSet.remove();
				//if necessary, rotate robot
				if(nextAction.getAction().equals("NORTH")){
					System.out.println("Next action: NORTH "+nextAction.getDistance());
					switch(orientation){
					case 0:
						System.out.println("    orientation = 0. TURNLEFT 90");
						communications.turnLeft();
						break;
					case 90:
						System.out.println("    orientation = 90. DO NOTHING");
						break;
						//already at correct orientation; do nothing
					case 180:
						System.out.println("    orientation = 180. TURNRIGHT 90");
						communications.turnRight();
						break;
					case 270:
						System.out.println("    orientation = 270. TURNRIGHT 180");
						communications.turnRight();
						communications.turnRight();
						break;
					}
				}else if(nextAction.getAction().equals("SOUTH")){
					System.out.println("Next action: SOUTH "+nextAction.getDistance());
					switch(orientation){
					case 0:
						System.out.println("    orientation = 0. TURNRIGHT 90");
						communications.turnRight();
						break;
					case 90:
						System.out.println("    orientation = 90. TURNRIGHT 180");
						communications.turnRight();
						communications.turnRight();
						break;
					case 180:
						System.out.println("    orientation = 180. TURNLEFT 90");
						communications.turnLeft();
						break;
					case 270:
						System.out.println("    orientation = 270. DO NOTHING");
						break;
						//already at correct orientation; do nothing
					}
				}else if(nextAction.getAction().equals("EAST")){
					System.out.println("Next action: SOUTH "+nextAction.getDistance());
					switch(orientation){
					case 0:
						System.out.println("    orientation = 0. DO NOTHING");
						break;
						//already at correct orientation; do nothing
					case 90:
						System.out.println("    orientation = 90. TURNRIGHT 90");
						communications.turnRight();
						break;
					case 180:
						System.out.println("    orientation = 180. TURNLEFT 180");
						communications.turnLeft();
						communications.turnLeft();
						break;
					case 270:
						System.out.println("    orientation = 270. TURNLEFT 90");
						communications.turnLeft();
						break;
					}
				}else if(nextAction.getAction().equals("WEST")){
					System.out.println("Next action: WEST "+nextAction.getDistance());
					switch(orientation){
					case 0:
						System.out.println("    orientation = 0. TURNRIGHT 180");
						communications.turnRight();
						communications.turnRight();
						break;
					case 90:
						System.out.println("    orientation = 90. TURNLEFT 90");
						communications.turnLeft();
						break;
					case 180:
						System.out.println("    orientation = 180. DO NOTHING");
						break;
						//already at correct orientation; do nothing
					case 270:
						System.out.println("    orientation = 270. TURNRIGHT 90");
						communications.turnRight();
						break;
					}
				}

				//send message to robot to take the next action
				communications.followLine(nextAction.getDistance());

				while(!foundNext){
					//do nothing until robot gets to next vertex
				}
				foundNext = false;
				//repeat
			}
		}
		communications.markRoadClosure();
		map.addClosure(map.getRobot().getRobotLocation());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void updateGraph(){
		graph.updateGraph();
	}
	
	public void initialiseGraph(){
		graph.initialiseGraph();
	}

}