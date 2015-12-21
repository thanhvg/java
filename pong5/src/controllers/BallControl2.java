package controllers;

import game.*;
import network2.Hub;
import sprites.*;

public class BallControl2 implements StateListener {
	Ball ball;
	//GameSingle game;
	Hub hub;
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

	public BallControl2(Ball ball, Hub hub) {
		super();
		this.ball = ball;
		this.hub = hub;
		hub.addListener(this);
	}

	public void move() {
		// move the ball, who calls this method? animator or ball?
		// the hub will call this after calling draw

		int[] a =  m.excute();
		updateXY(a);
	}

	@Override
	public void stateChange(Event e) {
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

	private void updateXY(int[] a) {
		ball.setX(a[0]);
		ball.setY(a[1]);

	}

	interface Movable {
		public int[] move();
	}

	class Move {
		Movable worker;

		public Move(Movable worker) {
			super();
			this.worker = worker;
		}

		public int[] excute() {
			return worker.move();

		}


	}

	class LeftToServe implements Movable {
		@Override
		public int[] move() {
			int[] a = {5, hub.getBarLeft().getY()};
			return a;
		}

	}

	class Waiting implements Movable {

		@Override
		public int[] move() {
			int[] a = {5, hub.getBarLeft().getY()};
			return a;
		}

	}
	class TouchedRight implements Movable {

		@Override
		public int[] move() {
			int y = ball.getY();

			int[] a = {Constant.BOX_WIDTH,y};
			return a;
		}

	}

	class MoveRight implements Movable {

		void bounce() {
			boolean b = isUp();
			setUp(!b);
		}

		@Override
		public int[] move() {			
			int y = ball.getY();
			int x = ball.getX();
			int[] a = new int[2];
			// int x1, y1;
			int dx = ball.getdX();
			int dy = ball.getdY();
			a[0] = x + dx; 

			if (isUp()) {
				a[1] = y - dy;
				if (a[1] < 0) bounce(); 
			} 

			if (!isUp()) {
				a[1] = y + dy;
				if (a[1] > Constant.BOX_HEIGHT) bounce();
			}

			if (a[0] > Constant.BOX_WIDTH) {
				if (a[1] > ball.getY() && a[1] < ball.getY() + Constant.BAR_HEIGHT)  {
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
		void bounce() {
			boolean b = isUp();
			setUp(!b);
		}

		@Override
		public int[] move() {
			int y = ball.getY();
			int x = ball.getX();
			int[] a = new int[2];
			int dx = ball.getdX();
			int dy = ball.getdY();
			a[0] = x - dx; 
			if (isUp()) {
				a[1] = y - dy;
				if (a[1] < 0) bounce(); 
			} 

			if (!isUp()) {
				a[1] = y + dy;
				if (a[1] > Constant.BOX_HEIGHT) bounce();
			}

			if (a[0] < 0) {
				if (a[1] > hub.getBarLeft().getY() && a[1] < hub.getBarLeft().getY() + Constant.BAR_HEIGHT)  {
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
		public int[] move() {
			int y = ball.getY();

			int[] a = {0,y};
			return a;
		}

	}

	class RightToServe implements Movable {

		@Override
		public int[] move() {

			int[] a = {5, hub.getBarRight().getY()};
			return a;
		}

	}

}








