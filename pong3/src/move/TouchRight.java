package move;

import pong2.Ball;
import pong2.GameConstants;

public class TouchRight implements Movable, GameConstants {
	@Override
	public void move(Ball ball, int yBar1, int yBar2) {
		ball.setX(BOX_WIDTH-BALL_SIZE);
	}
}
