package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controllers.*;
import sprites.*;

public class hub implements KeyBoardHandler{
	Ball ball;
	BarLeft barLeft;
	BarRight barRight;
	BallControl ballControl;
	LeftBarControl leftBarControl;
	RightBarControl rightBarControl;
	
	static List<StateListener> StateListenerList = new ArrayList<>();
	private static Map<State, Map<Event,State>> stateMap;
	private static Map<Event, State> eventMap;	
	public static State state;
	
	
	
	public Ball getBall() {
		return ball;
	}

	public BarLeft getBarLeft() {
		return barLeft;
	}

	public BarRight getBarRight() {
		return barRight;
	}

	public static State getState() {
		return state;
	}
	
	public static void setState(State state) {
		hub.state = state;
		
	}
	
	public static State whatsnext(Event event) {
		//given an event to the current state 
		eventMap = stateMap.get(state);
		return eventMap.get(event);				
	}
	
	public BallControl getBallControl() {
		return ballControl;
	}

	public void setBallControl(BallControl ballControl) {
		this.ballControl = ballControl;
	}

	public hub(Ball ball, BarLeft barLeft, BarRight barRight) {
		super();
		this.ball = ball;
		this.barLeft = barLeft;
		this.barRight = barRight;
		ballControl = new BallControl(ball,this);
		leftBarControl = new LeftBarControl(barLeft);
		rightBarControl = new RightBarControl(barRight, this);
		
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
	
	public static void addListener(StateListener sl) {
		StateListenerList.add(sl);
	}
	
	public static void eventCenter(Event e) {
		State s = whatsnext(e);
		if (s == null) {
			System.out.println("eventCenter unknow event " + e + " for state " + state );
			return;
		}
		setState(s);
		System.out.println("Event center state "+ s + " event " +e);
		for(StateListener sl : StateListenerList) {
			sl.stateChange(e);
			
		}
		
	}
	
	public void act() {
		ballControl.move();
		leftBarControl.move();
		rightBarControl.move();
	}

	@Override
	public void keyPressedHandle(KeyEvent e) {
		if (hub.getState() == State.TOUCHED_LEFT || 
		    hub.getState() == State.TOUCHED_RIGHT) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_SPACE) {
				hub.setState(State.TOUCHED_RIGHT);
				hub.eventCenter(Event.BALL_RETURNED);
				return;
			}
			
			
		}
		
		
		leftBarControl.keyPressedHandle(e);
		
	}

	@Override
	public void keyReleasedHandle(KeyEvent e) {
		leftBarControl.keyReleasedHandle(e);
		
	}

}
