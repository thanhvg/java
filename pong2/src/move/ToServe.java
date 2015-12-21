package move;

import pong2.Ball;
import pong2.GameConstants;

public class ToServe implements Movable, GameConstants {

	@Override
	public void move(Ball ball, int yBar1, int yBar2) {
		// TODO Auto-generated method stub
		// ball move with yBar2
		ball.setX(BOX_WIDTH-BAR_WIDTH-BALL_SIZE);
		ball.setY(yBar2);
		
	}


}
