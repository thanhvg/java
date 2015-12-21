package pong2;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import move.Move;

public class Ball extends Sprite implements GameConstants {

	BallState state;
	GUI gui;
	// Specbackup spec;
	Move moveClass = new Move();
	
	public BallState getState() { 
		return state;
	} 

	public void setState(BallState state) {
		this.state = state;
	}

	public Ball(int x, int y, int dx, int dy) {
		super(x,  y, dx, dy);
	}

	public Ball(int x, int y, int dx, int dy, BallState state) {
		super(x,  y, dx, dy);
		setState(state);
	}

	public Ball(int x, int y, int dx, int dy, BallState state, GUI gui) {
		super(x,  y, dx, dy);
		setState(state);
		this.gui = gui;
		//spec = new Specbackup(dx,dy);
	}


	public void draw (Graphics g) {
		g.fillOval(x,y,BALL_SIZE,BALL_SIZE);
	}
	
	public void move (int yBar1, int yBar2) {
		moveClass.move(this, yBar1, yBar2);
	}

	// private  void bounceBar() {
	// 	//	x = -x;
	// 	speedX = -speedX;
	// }

//	public void  move(int yBar1, int yBar2) {
//	    if (state == BallState.MOVELEFT) {
//		if (x < (BAR_WIDTH + COLLISION_TRIGGER)) {
//		    if ((yBar1 - y) > 0 && (yBar1 - y) < BAR_HEIGHT) { //hit bar1
//			speedX = -speedX;
//			state == BallState.MOVERIGHT;
//		    } else { // hit left side box
//			x = 0;
//			speedX = 0;
//			speedY = 0;
//			state = BallState.TOUCHLEFT;
//		    }
//		} 
//		if (x >= (BAR_WIDTH + COLLISION_TRIGGER)) {
//		    if ( y < 0 || y > (BOX_WIDTH - BALL_SIZE)) {
//			speedY = -speedY;
//		}
//
//		x += speedX;
//		y += speedY;
//		return;
//	    }
//
//	    if (state == BallState.MOVERIGHT) {
//	    }
//
//	    if (state == BallState.TOSERVE) {
//	    }
//
//
//	} // end of move
		
	    //		// check collision with bar 1
//		if ((x < COLLISION_TRIGGER) && (state == BallState.MOVELEFT)) {
//			// in the bar1 side
//			System.out.printf("In move bar 1 side");
//			if ((yBar1 - y) > 0 && (yBar1 - y) < BAR_HEIGHT) {
//				// hit the bar 1
//				bounceBar();
//				state = BallState.MOVERIGHT;
//			} else { // hit side bar
//				x = 0;
//				saveSpec();
//				state = BallState.TOUCHLEFT;
//				dx = 0;
//				dy = 0;
//			}
//			return;
//		}
//		if (x > (BOX_WIDTH - COLLISION_TRIGGER) && state == BallState.MOVERIGHT)  { 
//			// in the bar 2  side 
//			System.out.printf("In move bar 2 side");
//			if ((yBar2 - y) > 0 && (yBar2 - y) < BAR_HEIGHT) {
//				// hit the bar 2
//				bounceBar();
//				state = BallState.MOVELEFT;
//			} else { // hit side bar
//				x = BOX_WIDTH-BALL_SIZE;
//				state = BallState.TOUCHRIGHT;
//				saveSpec();
//				dx = 0;
//				dy = 0;
//			}
//			return;
//		}
//		//System.out.printf("In move bar 2 side");
//		//System.out.printf("x > COLLISION_TRIGGER is %s\n",(x > COLLISION_TRIGGER) );
//		//System.out.printf("x < (BOX_WIDTH - COLLISION_TRIGGER) is %s\n",x < BOX_WIDTH - COLLISION_TRIGGER );
//		
//		if ((x >= COLLISION_TRIGGER) 
//				&& (x <= (BOX_WIDTH - COLLISION_TRIGGER)) 
//				/*&& ((state == BallState.MOVERIGHT)||(state == BallState.MOVELEFT))*/) {
//			// inside 		// 
//			if ((y > BOX_HEIGHT) || (y < 0)) {
//				//	y = -y;
//				dy = -dy;
//			}
//			x = x + dx;
//			y = y + dy;
//			//System.out.printf("In move x %d and y %d and state %s\n",x,y,state);
//		}
//	} // end of move

	//listen to key when serve ball
//	public void keyPressed(KeyEvent e) {
//
//		int key = e.getKeyCode();
//
//		if (key == KeyEvent.VK_UP) {
//			dy = -1;
//		}
//
//		if (key == KeyEvent.VK_DOWN) {
//			dy = 1;
//		}
//	}

//	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
//
//
//		if (key == KeyEvent.VK_UP) {
//			dy = 0;
//		}
//
//		if (key == KeyEvent.VK_DOWN) {
//			dy = 0;
//		}
//	}

//	public void saveSpec() {
//		if (spec.isSaved()) { //lazy implement
//			return;
//		}
//		spec.dx = dx;
//		spec.dy = dy;
//		spec.setSave();
//	}

//	public void loadSpec() {
//		if (spec.isSaved()) { //lazy implement
//			dx = spec.dx;
//			dy = spec.dy;
//		}
//	}


//	public void serve(int yBar1, int yBar2) {
//		loadSpec();
//		System.out.printf("Spec %d, %d\n", spec.dx, spec.dy);
//		if (x > BOX_WIDTH/2) { // on the bar 2 side
//			state = BallState.MOVELEFT;
//			dx = -Math.abs(dx);
//			move(yBar1,yBar2);
//			System.out.printf("yBar1 %d and yBa2 %d\n",yBar1,yBar2);
//			System.out.printf("x %d and y %d and state %s\n",x,y,state);
//			System.out.printf("dx %d and dy %d and state %s\n",dx,dy,state);
//		}
//		if (x < BOX_WIDTH/2) { // on the bar 1 side
//			state = BallState.MOVERIGHT;
//			dx = Math.abs(dx);
//			move(yBar1,yBar2);
//		}
//	}
	
//	private class Specbackup {
//		int dx, dy;
//		boolean save = false;
//		boolean isSaved() {
//			return save;
//		}
//		void setSave() {
//			save = true;
//		}
//		Specbackup(int dx, int dy) {
//			this.dx = dx;
//			this.dy = dy;
//		}
//	}
	
	public boolean isHittingLeftBar(int yBar1) {
		if (x <= BAR_WIDTH && (y - yBar1) >= 0 && (y - yBar1) <= (BAR_HEIGHT-BALL_SIZE)) return true;
		return false;		
	}
	
	public boolean isHittingLeftSide(int yBar1) {
		if ((x <= BAR_WIDTH)&&(isHittingLeftBar(yBar1)==false)) return true;	
		return false;		
	}
	
	public boolean isHittingRightBar(int yBar2) {
		if (x >= BOX_WIDTH-BAR_WIDTH-BALL_SIZE && y - yBar2   >= 0 && y - yBar2  <= BAR_HEIGHT-BALL_SIZE) return true;
		return false;		
	}
	
	public boolean isHittingRightSide(int yBar2) {
		if ((x >= BOX_WIDTH-BAR_WIDTH-BALL_SIZE)&&(isHittingRightBar(yBar2)==false)) return true;	
		return false;		
	}
	
	public boolean isInside(int x) {
		if ((x > BAR_WIDTH) && (x < BOX_WIDTH-BAR_WIDTH-BALL_SIZE)) {
			return true;
		}
		return false;
	}
	
	public boolean isHittingYbar() {
		if (y >= BOX_HEIGHT - BALL_SIZE || y <= 0) return true;
		return false;
	}
	
	public void moveXY() {
		x = x + dx;
		y = y + dy;
	}

	public void bounceX() {
		dx = -dx;
	}
	
	public void bounceY() {
		dy = -dy;
	}
}

