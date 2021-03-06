package pong2;

import java.awt.Graphics;

public class Bar1 extends Sprite implements GameConstants {
	GUI gui;

	public void draw(Graphics g) {
		g.fillRect(x, y, BAR_WIDTH,BAR_HEIGHT);
	}

	public void move(int ballX, int ballY, BallState bState) {
		if (bState == BallState.MOVELEFT) {
			//move with the ball
			if ((y + BAR_HEIGHT/2)> ballY && y > 0) {
				y -= dy; 
			} else if ((y+BAR_HEIGHT/2) < ballY && (y+BAR_HEIGHT)<BOX_WIDTH) {
				y += dy;
			}
		} else if (bState != BallState.TOUCHLEFT) {
			//move to center
			if ((y+BAR_HEIGHT/2)>BOX_HEIGHT/2) {
				y -= dy;
			} else {
				y +=dy;
			}
		}
		//y = y+dy;
		//System.out.printf("In bar 1 move y %d and dy %d\n",y,dy);


	} // end of move 

	public Bar1(int x, int y, int dx, int dy) {
		super(x,  y, dx, dy);
	}

	public Bar1(int x, int y, int dx, int dy, GUI gui) {
		super(x,  y, dx, dy);
		this.gui = gui;
	}
}
