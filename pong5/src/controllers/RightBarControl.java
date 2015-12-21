package controllers;

import game.*;
import sprites.*;

public class RightBarControl implements StateListener {
	BarRight barRight;
	RightBarControllWorker worker;
	hub gs;
	int y;
	
	public RightBarControl(BarRight barRight, hub gs) {
		super();
		this.barRight = barRight;
		this.gs = gs;
		if (hub.getState() == State.MOVING_RIGHT)
			worker = new RightBarControllWorker(new MoveToHit());
		else 
			worker = new RightBarControllWorker(new MoveToCenter());
			
		hub.addListener(this);
	}

	public void move() {
		y = worker.doit(gs);
		updateY();
	}

	private void updateY() {
		gs.getBarRight().setY(y);		
	}

	@Override
	public void stateChange(Event e) {
		// TODO Auto-generated method stub
		if (hub.getState() == State.MOVING_RIGHT)
			worker = new RightBarControllWorker(new MoveToHit());
		else 
			worker = new RightBarControllWorker(new MoveToCenter());
	}

}

interface RightBarMovable {
	public int move(hub gs);
}

class RightBarControllWorker {
	RightBarMovable worker;
	
	public RightBarControllWorker(RightBarMovable worker) {
		super();
		this.worker = worker;
	}

	public int doit(hub gs) {
		return worker.move(gs);
	}
	
}

class MoveToCenter implements RightBarMovable {
	@Override
	public int move(hub gs) {
		int y = gs.getBarRight().getY();
		if (y > Constant.BOX_HEIGHT/2 - Constant.BAR_HEIGHT/2)
		return y - gs.getBarRight().getdY();
		return y + gs.getBarRight().getdY();
	}
}

class MoveToHit implements RightBarMovable {

	@Override
	public int move(hub gs) {
		int yBar = gs.getBarRight().getY();
		int y = gs.getBarRight().getY();
		int yBall = gs.getBall().getY();
		if (yBall < y) {
			yBar = y - gs.getBarRight().getdY();
		}
		if (yBall > y + Constant.BAR_HEIGHT) {
			yBar = y + gs.getBarRight().getdY();
		}
		
		if (yBar < 0) yBar = 0;
		if (yBar > Constant.BOX_HEIGHT) yBar = Constant.BOX_HEIGHT;
		System.out.println("Move to hit yBar "+ yBar);
		return yBar;
	}
	
}



