package controllers;

import game.*;
import sprites.*;

public class BallControl implements StateListener {
	Ball ball;
	hub game;
	int x;
	int y;
//	int dx = ball.getdX();
//	int dy = ball.getdY();
//	int orgX = x;
//	int orgY = y;
//	int orgDx = dx;
//	int orgDy = dy;
	boolean up = true;
	
	public boolean isUp() {
		return up;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}

	Move m = new Move(new Waiting());
	
	public BallControl(Ball ball, hub game) {
		super();
		this.ball = ball;
		this.game = game;
		int x = ball.getX();
		int y = ball.getY();
		hub.addListener(this);
	}

	public void move() {
		// move the ball, who calls this method? animator or ball?
		// the game will call this after calling draw
		
		int[] a =  m.excute(ball.getX(), ball.getY(), game);
		x = a[0];
		y = a[1];
		
		updateXY();
	}

	@Override
	public void stateChange(Event e) {
		// TODO Auto-generated method stub
		switch (e) {
		case START:
			m = new Move(new LeftToServe());
			break;		

		case BALL_SERVED : 
			if (hub.getState() == State.MOVING_RIGHT) 
				m = new Move(new MoveRight());
			if (hub.getState() == State.MOVING_LEFT)
				m = new Move(new MoveLeft());
			break;
		case BALL_RETURNED:
			if (hub.getState() == State.LEFT_TO_SERVE)
				m = new Move(new LeftToServe());
			if (hub.getState() == State.RIGHT_TO_SERVE)
				m = new Move(new RightToServe());
			break;
		case BALL_TOUCHED_RIGHT:
			m = new Move(new TouchedRight());
			break;
		case BALL_TOUCHED_LEFT:
			m = new Move(new TouchLeft());
			break;
		case BALL_TOUCHED_LEFT_BAR:
			m = new Move(new MoveRight());
			break;
		case BALL_TOUCHED_RIGHT_BAR:
			m = new Move(new MoveLeft());
			break;
		default:
			System.out.println("Unknown event in BallControll stateChange");
			break;
				
		}	
			
	}
	
	private void updateXY() {
		ball.setX(x);
		ball.setY(y);
		
	}

}

interface Movable {
	public int[] move(int x, int y, hub game);
}

class Move {
	Movable worker;
	
	public Move(Movable worker) {
		super();
		this.worker = worker;
	}

	public int[] excute(int x, int y, hub game) {
		return worker.move(x,y, game);
	}
}

class LeftToServe implements Movable {
	@Override
	public int[] move(int x, int y, hub game) {
		int[] a = {5, game.getBarLeft().getY()};
		return a;
	}
	
}

class Waiting implements Movable {

	@Override
	public int[] move(int x, int y, hub game) {
		int[] a = {5, game.getBarLeft().getY()};
		return a;
	}
	
}
class TouchedRight implements Movable {

	@Override
	public int[] move(int x, int y, hub game) {
		int[] a = {Constant.BOX_WIDTH,y};
		return a;
	}
	
}

class MoveRight implements Movable {
	
	void bounce(hub game) {
		boolean b = game.getBallControl().isUp();
		game.getBallControl().setUp(!b);
	}

	@Override
	public int[] move(int x, int y, hub game) {
		int[] a = new int[2];
		// int x1, y1;
		int dx = game.getBall().getdX();
		int dy = game.getBall().getdY();
		a[0] = x + dx; 
		
		if (game.getBallControl().isUp()) {
			a[1] = y - dy;
			if (a[1] < 0) bounce(game); 
		} 
		
		if (!game.getBallControl().isUp()) {
			a[1] = y + dy;
			if (a[1] > Constant.BOX_HEIGHT) bounce(game);
		}
		
		if (a[0] > Constant.BOX_WIDTH) {
			if (a[1] > game.getBarRight().getY() && a[1] < game.getBarRight().getY() + Constant.BAR_HEIGHT)  {
				System.out.println("Hit");
				new Thread() {
					public void run(){
						hub.eventCenter(Event.BALL_TOUCHED_RIGHT_BAR);
					}

				}.start();
			} else {			
				new Thread() {
					public void run() {
						hub.eventCenter(Event.BALL_TOUCHED_RIGHT);
					}

				}.start();
			}


		}		
		return a;
	}
	
}

class MoveLeft implements Movable {
	void bounce(hub game) {
		boolean b = game.getBallControl().isUp();
		game.getBallControl().setUp(!b);
	}

	@Override
	public int[] move(int x, int y, hub game) {
		int[] a = new int[2];
		int dx = game.getBall().getdX();
		int dy = game.getBall().getdY();
		a[0] = x - dx; 
		if (game.getBallControl().isUp()) {
			a[1] = y - dy;
			if (a[1] < 0) bounce(game); 
		} 
		
		if (!game.getBallControl().isUp()) {
			a[1] = y + dy;
			if (a[1] > Constant.BOX_HEIGHT) bounce(game);
		}
		
		if (a[0] < 0) {
			if (a[1] > game.getBarLeft().getY() && a[1] < game.getBarLeft().getY() + Constant.BAR_HEIGHT)  {
				new Thread() {
					public void run() {
						hub.eventCenter(Event.BALL_TOUCHED_LEFT_BAR);
					}
				}.start();
			} else {
				new Thread() {
					public void run (){
						hub.eventCenter(Event.BALL_TOUCHED_LEFT);
					}
				}.start();
			}
		}
		return a;
	}
	
}

class TouchLeft implements Movable {

	@Override
	public int[] move(int x, int y, hub game) {
		int[] a = {0,y};
		return a;
	}
	
}

class RightToServe implements Movable {

	@Override
	public int[] move(int x, int y, hub game) {
		int[] a = {5, game.getBarRight().getY()};
		return a;
	}
	
}


