package artificialIntelligenceUnit;

/**
 * @author Matthew Nestor
 * @filename Node.java
 * @package artificialIntelligenceUnit
 * @project HostSide
 * @date 16/10/2013
 */

public class Node{

	private int queuePriority;
	private Node parent;
	private String action;
	private int pathCost;
	private Vertex state;
	private boolean initial;
	private boolean localInitial;

	public Node(Vertex state, int qp){
		this.state = state;
		pathCost = 0;
		initial = true;
		queuePriority = qp;
		localInitial = false;
	}

	public Node(Vertex state, Node parent, String action, int qp, int pathCost){
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.pathCost = pathCost;
		initial = false;
		localInitial = false;
		queuePriority = qp;
	}

	public void setParent(Node parent){
		this.parent = parent;
	}

	public void setPathCost(int pathCost){
		this.pathCost = pathCost;
	}

	public Vertex getState(){
		return state;
	}

	public int getPathCost(){
		return pathCost;
	}

	public String getAction(){
		return action;
	}

	public Node getParent(){
		return parent;
	}

	public double getAStarCost(){
		return (double)pathCost + state.getHeuristic();
	}

	public void setInitial(){
		initial = true;
	}

	public boolean isInitial(){
		return initial;
	}

	public int getPriority(){
		return queuePriority;
	}

	public void setLocalInitial(){
		localInitial = true;
	}

	public boolean isLocalInitial(){
		return localInitial;
	}

}