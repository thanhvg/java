package game;

import java.util.HashMap;
import java.util.Map;

import sprites.*;

public class GameSingleTest {
	public static State state;
	
	public static State getState() {
		return state;
	}
	
	public static void setState(State state) {
		GameSingleTest.state = state;
	}
	
	public static State whatsnext(Event event) {
		//given an event to the current state 
		eventMap = stateMap.get(state);
		return eventMap.get(event);				
	}
	
	private static Map<State, Map<Event,State>> stateMap;
	private static Map<Event, State> eventMap;

	public GameSingleTest() {
		super();
		stateMap = new HashMap<State, Map<Event,State>>();
		eventMap = new HashMap<Event,State>();
		// Waiting state, to PL state with event Start, from all state with event stop
		// Events that applies to all of state: Stop, Pause are processed with if then
		eventMap.put(Event.START, State.LEFT_TO_SERVE);
		stateMap.put(State.WAITIING, eventMap);
		// eventMap.clear(); Use this you'll get the null eventMap
		eventMap = new HashMap<Event,State>();
		// Left to serve state, events ball served to state Move Right, event Ball return from state touched right
		eventMap.put(Event.BALL_SERVED, State.MOVING_RIGHT);
		stateMap.put(State.LEFT_TO_SERVE, eventMap);
		// eventMap.clear();
		eventMap = new HashMap<Event,State>();
		// Moving right, event ball touched right,  ball  touched right bar
		// We only care about next states, not state that jumps to this state
		eventMap.put(Event.BALL_TOUCHED_RIGHT, State.TOUCHED_RIGHT);
		eventMap.put(Event.BALL_TOUCHED_RIGHT_BAR, State.MOVING_LEFT);
		stateMap.put(State.MOVING_RIGHT, eventMap);
		// eventMap.clear();
		eventMap = new HashMap<Event,State>();
		// State touch right event ball returned
		eventMap.put(Event.BALL_RETURNED,State.LEFT_TO_SERVE);
		stateMap.put(State.TOUCHED_RIGHT, eventMap);
		// eventMap.clear();
		eventMap = new HashMap<Event,State>();
		// State moving left 
		eventMap.put(Event.BALL_TOUCHED_LEFT, State.TOUCHED_LEFT);
		eventMap.put(Event.BALL_TOUCHED_LEFT_BAR, State.MOVING_RIGHT);
		stateMap.put(State.MOVING_LEFT,eventMap);
		//eventMap.clear();
		eventMap = new HashMap<Event,State>();
		// State touched left
		eventMap.put(Event.BALL_RETURNED, State.RIGHT_TO_SERVE);
		stateMap.put(State.TOUCHED_LEFT,eventMap);
		//eventMap.clear();
		eventMap = new HashMap<Event,State>();
		// State right to serve
		eventMap.put(Event.BALL_SERVED, State.MOVING_LEFT);
		stateMap.put(State.RIGHT_TO_SERVE, eventMap);
		// eventMap.clear();		
	}
	
	public static void main(String[] args) {
		GameSingleTest game = new GameSingleTest();
		System.out.println(stateMap);
		for (State s : State.values()) {
			GameSingleTest.setState(s);
			System.out.println("Current state is " + s);
			Map<Event,State> m = new HashMap<Event,State>();
			for (Event e : Event.values()) {
				System.out.println(" event " + e + " next state " + GameSingleTest.whatsnext(e));
			}
		}
	}
}
