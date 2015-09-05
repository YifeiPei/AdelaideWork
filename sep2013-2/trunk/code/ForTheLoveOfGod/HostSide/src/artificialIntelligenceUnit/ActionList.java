package artificialIntelligenceUnit;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Matthew Nestor
 * @filename ActionList.java
 * @package artificialIntelligenceUnit
 * @project HostSide
 * @date 25/10/2013
 */

public class ActionList {
	private Deque<Action> actions;
	
	public ActionList(){
		actions = new ArrayDeque<Action>();
	}
		
	public void addAction(Action a){
		actions.add(a);
	}
	
	public Action getAction(){
		return actions.remove();
	}
	
	public boolean isEmpty(){
		return actions.isEmpty();
	}
}
