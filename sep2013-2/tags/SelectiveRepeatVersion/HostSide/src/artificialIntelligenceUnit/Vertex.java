package artificialIntelligenceUnit;

import java.util.*;
import java.awt.*;

/**
 * @author Matthew Nestor
 * @filename Vertex.java
 * @package artificialIntelligenceUnit
 * @project HostSide
 * @date 16/10/2013
 */

public class Vertex {

	private double heuristic;
	private int index;
	private Point coordinate;
	private LinkedList<Edge> edges;
	private boolean isGoal;
	private boolean isInitial;

	public Vertex(int index, Point p){
		edges = new LinkedList<Edge>();
		coordinate = p;
		this.index = index;
		isGoal = false;
	}
	
	public Vertex(int index, Point p, boolean b){
		edges = new LinkedList<Edge>();
		coordinate = p;
		this.index = index;
		isGoal = b;
	}
	
	public boolean isGoal(){
		return isGoal;
	}

	public void addEdge(Edge e){
		edges.add(e);
	}

	public int index(){
		return index;
	}
	
	public void setInitial(boolean b){
		isInitial = b;
	}
	
	public boolean isInitial(){
		return isInitial;
	}

	public void setHeuristic(double h){
		heuristic = h;
	}

	public double getHeuristic(){
		return heuristic;
	}

	public Point getCoordinate(){
		return coordinate;
	}

	public void removeEdge(Vertex toVertex){
		for(int j = 0; j < edges.size(); j++){
			Edge e = edges.get(j);
			if(e.toVertex()==toVertex)
				edges.remove(j);
		}
	}

	public LinkedList<Edge> outEdgesOf(){
		return edges;
	}

}
