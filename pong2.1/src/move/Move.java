package move;

import pong2.Ball;
import pong2.BallState;

public class Move {
	Movable movable;

	public void move(Ball ball, int yBar1, int yBar2) {
		BallState state = ball.getState();

		switch (state) {
			case MOVELEFT: 	movable = new MoveLeft(); break;
			case MOVERIGHT: movable = new MoveRight(); break;
			case STOPPED: 	movable = new Stopped(); break;
			case TOSERVE:	movable = new ToServe(); break;
			case TOUCHLEFT: movable = new TouchLeft(); break;
			case TOUCHRIGHT:movable = new TouchRight(); break;
			case SERVE: 	movable = new Serve(); break;
			default:		movable = new Stopped(); break;				
		}
		movable.move(ball, yBar1, yBar2);
	}
	
	

}
