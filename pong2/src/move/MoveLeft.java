package move;

import pong2.Ball;
import pong2.BallState;
import pong2.GameConstants;

public class MoveLeft implements GameConstants, Movable {

	@Override
	public void move(Ball ball, int yBar1, int yBar2) {
		// TODO Auto-generated method stub
//		int speedX = ball.getdX();
//		int speedY = ball.getdX();
		
		if (ball.isHittingLeftSide(yBar1)) {
			ball.setState(BallState.TOUCHLEFT);
			System.out.printf("Hit side yBar1 %d  yBall %d",yBar1,ball.getY());
			return;
		}
		
		if  (ball.isHittingLeftBar(yBar1)) {
			ball.bounceX();	
			ball.setState(BallState.MOVERIGHT);
			System.out.printf("Hit bar");
		}
		
		if (ball.isHittingYbar()) {
			ball.bounceY();
		}
		
		ball.moveXY();
	}

}
