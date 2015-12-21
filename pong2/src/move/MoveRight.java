package move;

import pong2.Ball;
import pong2.BallState;
import pong2.GameConstants;

public class MoveRight implements GameConstants, Movable {

	@Override
	public void move(Ball ball, int yBar1, int yBar2) {
		// TODO Auto-generated method stub
		if (ball.isHittingRightSide(yBar2)) {
			ball.setState(BallState.TOUCHRIGHT);
			return;
		}
		
		if (ball.isHittingRightBar(yBar2)) {
			ball.bounceX();
			ball.setState(BallState.MOVELEFT);
		}
		
		if (ball.isHittingYbar()) {
			ball.bounceY();
		}
		
		ball.moveXY();
	}

}
