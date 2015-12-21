package move;

import pong2.Ball;
import pong2.BallState;
import pong2.GameConstants;

public class Serve implements Movable, GameConstants{

	@Override
	public void move(Ball ball, int yBar1, int yBar2) {
		// TODO Auto-generated method stub
		int s = ball.getdX();
		s = - Math.abs(s);
		ball.setdX(s);
		
		ball.setState(BallState.MOVELEFT);
		ball.moveXY();
		
	}

}
