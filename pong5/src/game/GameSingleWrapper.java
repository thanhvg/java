package game;

import java.awt.event.KeyEvent;

public class GameSingleWrapper implements Controllable, KeyBoardHandler {
	hub gs;
	

	public GameSingleWrapper(hub gs) {
		super();
		this.gs = gs;
	}

	@Override
	public void act() {
		gs.act();
		
	}

	@Override
	public void addListener(StateListener sl) {
		hub.addListener(sl);		
	}

	@Override
	public void keyPressedHandle(KeyEvent e) {
		gs.keyPressedHandle(e);
		
	}

	@Override
	public void keyReleasedHandle(KeyEvent e) {
		gs.keyReleasedHandle(e);
		
	}

}
