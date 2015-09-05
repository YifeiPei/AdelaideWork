import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Yifei Pei with the student ID a1611648
 *
 */

public class FsaImpl implements Fsa, FsaSim 
{
	private Set<State> states;
	private Set<Transition> transitions;
	private ArrayList<FsaListener> FsaListeners;
	private Set<State> initialStates;
	private Set<State> finalStates;
	private Set<State> currentStates;

	public FsaImpl ()
	{
		states = new HashSet<State> ();
		transitions = new HashSet<Transition> ();
		FsaListeners = new ArrayList<FsaListener> ();
		initialStates = new HashSet<State> ();
		finalStates = new HashSet<State> ();
		currentStates = new HashSet<State> ();
	}

	@Override
	public void reset() 
	{
		currentStates.clear();
		this.getInitialStates();
		currentStates.addAll(initialStates);
		for (State s: initialStates)
			currentStates.addAll(this.eclose(s));
	}

	@Override
	public void step(String event) 
	{
		Set<State> temp = new HashSet<State> ();
		Set<State> temp2 = new HashSet<State> ();
		for (Transition t : transitions) {
			if (t.eventName().equals(event)) {
				temp.addAll(this.eclose(t.fromState()));
				for (Transition tr : transitions) {
					for (State s: temp) {
						if (tr.fromState().getName().equals(s.getName()))
							temp2.add(tr.toState());
					}
				}
				if (currentStates.contains(t.fromState()))
					currentStates.remove(t.fromState());
				currentStates.add(t.toState());
				currentStates.addAll(temp);
				currentStates.addAll(temp2);
				for (State s: currentStates)
					currentStates.addAll(this.eclose(s));
			}
		}
	}

	@Override
	public boolean isRecognised() 
	{
		for (State s : currentStates) {
			if (s.isFinal() == true)
				return true;
		}
		return false;
	}

	@Override
	public State newState(String name, int x, int y)
			throws IllegalArgumentException 
			{
		if (findState(name) != null)
			throw new IllegalArgumentException("State has already existed");
		State newState = new StateImpl (name,x,y);
		states.add(newState);
		for (FsaListener fl : FsaListeners)
			fl.statesChanged();
		return newState;
			}

	@Override
	public void removeState(State s) 
	{
		if (s != null && states.contains(s)) {
			states.remove(s);
			transitions.removeAll(s.transitionsFrom());
			transitions.removeAll(s.transitionsTo());
			if (initialStates.contains(s))
				initialStates.remove(s);
			if (finalStates.contains(s))
				finalStates.remove(s);
			if (currentStates.contains(s))
				currentStates.remove(s);
			for (FsaListener fl : FsaListeners)
				fl.statesChanged();
		}
	}

	@Override
	public State findState(String stateName) 
	{
		if (states.size() > 0) {
			for (State s : states) {
				if (s.getName().equals(stateName))
					return s;
			}
		}
		return null;
	}

	@Override
	public Set<State> getStates() 
	{
		Set<State> States = new HashSet<State> ();
		States.addAll(states);
		return States;
	}

	@Override
	public Transition newTransition(State fromState, State toState,
			String eventName) throws IllegalArgumentException 
			{
		if (fromState == null || toState == null)
			throw new IllegalArgumentException("Transition cannot be connected");
		if (findState(fromState.getName()) == null || findState(toState.getName()) == null)
			throw new IllegalArgumentException("Transition cannot be connected");
		Transition newTransition = new TransitionImpl (fromState, toState, eventName);
		transitions.add(newTransition);
		for (FsaListener fl : FsaListeners)
			fl.transitionsChanged();
		return newTransition;
			}

	@Override
	public void removeTransition(Transition t) 
	{
		if (t != null && transitions.contains(t))
			transitions.remove(t);
		for (FsaListener fl : FsaListeners)
			fl.transitionsChanged();
	}

	@Override
	public Set<Transition> findTransition(State fromState, State toState) 
	{
		if (fromState == null || toState == null)
			throw new IllegalArgumentException("Invaid Trasition");
		Set<Transition> solution = new HashSet<Transition> ();
		for(Transition t : transitions) {
			if (t.fromState().getName().equals(fromState.getName()) && (t.toState().getName().equals(toState.getName())))
				solution.add(t);
		}
		return solution;
	}

	@Override
	public Set<State> getInitialStates() 
	{
		initialStates.clear();
		for (State s : states) {
			if (s.isInitial() == true)
				initialStates.add(s);
		}
		return initialStates;
	}

	@Override
	public Set<State> getFinalStates() 
	{
		finalStates.clear();
		for (State s : states) {
			if (s.isFinal() == true)
				finalStates.add(s);
		}
		return finalStates;
	}

	@Override
	public Set<State> getCurrentStates() 
	{
		return currentStates;
	}

	@Override
	public String toString() {
		String Fsa = "";
		if (states.size() != 0) {
			for (State s : states)
				Fsa += "state " + s.toString() + "\n";
		}
		if (transitions.size() != 0) {
			for (Transition t : transitions)
				Fsa += "transition " + t.toString() + "\n";
		}
		if (initialStates.size() != 0) {
			for (State s : initialStates)
				Fsa += "initial " + s.getName() + "\n";
		}
		if (finalStates.size() != 0) {
			for (State s : finalStates)
				Fsa += "final " + s.getName() + "\n";
		}
		return Fsa;
	}

	@Override
	public void addListener(FsaListener fl) 
	{
		FsaListeners.add(fl);
	}

	@Override
	public void removeListener(FsaListener fl) 
	{
		if (FsaListeners.contains(fl))
			FsaListeners.remove(fl);
	}

	public Set<State> eclose (State s) {
		Set<State> eclose = new HashSet<State> ();
		eclose.add(s);
		for (Transition t: transitions) {
			if (t.fromState().getName().equals(s.getName()) && ((t.eventName().equals("")) || (t.eventName() == null) || (t.eventName().equals("?")))) {
				if (t.toState().getName().equals(s.getName())) {
					break;
				} else {
					for (State st: eclose) {
						if (t.toState().getName().equals(st.getName())) {
							break;
						}
					}
					eclose.add(t.toState());
					eclose (t.toState());
				}
			}
		}
		return eclose;
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	class StateImpl implements State {

		private String stateName;
		private int xPos;
		private int yPos;
		private boolean isInitial;
		private boolean isFinal;
		private ArrayList<StateListener> StateListeners;
		private Set<Transition> transitionsFrom;
		private Set<Transition> transitionsTo;

		public StateImpl () {
			stateName = null;
			xPos = 0;
			yPos = 0;
			isInitial = false;
			isFinal = false;
			StateListeners = new ArrayList<StateListener> ();
			transitionsFrom = new HashSet<Transition> ();
			transitionsTo = new HashSet<Transition> ();
		}

		public StateImpl (String statename, int x, int y) {
			stateName = statename;
			xPos = x;
			yPos = y;
			isInitial = false;
			isFinal = false;
			StateListeners = new ArrayList<StateListener> ();
			transitionsFrom = new HashSet<Transition> ();
			transitionsTo = new HashSet<Transition> ();
		}

		@Override
		public void addListener(StateListener sl) {
			StateListeners.add(sl);
		}

		@Override
		public void removeListener(StateListener sl) {
			if (StateListeners.contains(sl))
				StateListeners.remove(sl);
		}

		@Override
		public Set<Transition> transitionsFrom() {
			transitionsFrom.clear();
			for (Transition t : transitions) {
				if (t.fromState().getName().equals(stateName))
					transitionsFrom.add(t);   		
			}
			return transitionsFrom;
		}

		@Override
		public Set<Transition> transitionsTo() {
			transitionsTo.clear();
			for (Transition t : transitions) {
				if (t.toState().getName().equals(stateName))
					transitionsTo.add(t);
			}
			return transitionsTo;
		}

		@Override
		public void moveBy(int dx, int dy) {
			xPos = xPos + dx;
			yPos = yPos + dy;
			for (StateListener sl : StateListeners)
				sl.StateHasChanged();
		}

		@Override
		public String toString() {
			String state = "";
			String j = "";
			String k = "";
			if (isInitial == true)
				j = "1";
			else
				j = "0";
			if (isFinal == true)
				k = "1";
			else
				k = "0";
			state = stateName + "(" + xPos + "," + yPos + ")" + j + k;
			return state;
		}

		@Override
		public String getName() {
			return stateName;
		}

		@Override
		public int getXpos() {
			return xPos;
		}

		@Override
		public int getYpos() {
			return yPos;
		}

		@Override
		public void setInitial(boolean b) {
			if (b == true) {
				isInitial = true;
				initialStates.add(this);
				for (FsaListener fl : FsaListeners)
					fl.otherChanged();
				for (StateListener sl : StateListeners)
					sl.StateHasChanged();
			}
			else
				isInitial = false;
		}

		@Override
		public boolean isInitial() {
			return isInitial;
		}

		@Override
		public void setFinal(boolean b) {
			if (b == true) {
				isFinal = true;
				finalStates.add(this);
				for (FsaListener fl : FsaListeners)
					fl.otherChanged();
				for (StateListener sl : StateListeners)
					sl.StateHasChanged();
			}
			else
				isFinal = false;
		}

		@Override
		public boolean isFinal() {
			return isFinal;
		}

		@Override
		public boolean isCurrent() {
			for (State s: currentStates) {
				if (s.getName().equals(this.getName()))
					return true;
			}
			return false;
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////

	class TransitionImpl implements Transition {

		private ArrayList<TransitionListener> TransitionListeners;
		private State fromState;
		private State toState;
		private String eventName;

		public TransitionImpl () {
			TransitionListeners = new ArrayList<TransitionListener> ();
			fromState = null;
			toState = null;
			eventName = null;
		}

		public TransitionImpl (State From, State To, String name) {
			TransitionListeners = new ArrayList<TransitionListener> ();
			fromState = From;
			toState = To;
			eventName = name;
		}

		@Override
		public void addListener(TransitionListener tl) {
			TransitionListeners.add(tl);
		}

		@Override
		public void removeListener(TransitionListener tl) {
			if (TransitionListeners.contains(tl))
				TransitionListeners.remove(tl);
		}

		@Override
		public State fromState() {
			return fromState;
		}

		@Override
		public State toState() {
			return toState;
		}

		@Override
		public String eventName() {
			return eventName;
		}

		@Override
		public String toString() {
			String transition = "";
			String EventName = eventName;
			if (eventName == null)
				EventName = "";
			transition = fromState.getName() + "(" + EventName + ")" + toState.getName();
			return transition;
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////	

}
