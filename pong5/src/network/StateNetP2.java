package network;

import game.Constant;
import game.State;

public class StateNetP2 {
	/**
	 * Convert StateNet to Player 2 perspective
	 */
	
	int p1; // player 1 y position
	int p2; // player 2 y position
	int ballY;
	int ballX;
	State gameState;
	
	public StateNetP2(StateNet stateNet){
		// y coordinates stay the same
		this.p1 = stateNet.getP1();
		this.p2 = stateNet.getP2();
		this.ballY = stateNet.getBallY();
		// x coordinate flip
		this.ballX = Constant.BOX_WIDTH - stateNet.getBallX();
		// state flips
		switch (stateNet.getGameState()) {
		case LEFT_TO_SERVE: 	this.gameState = State.RIGHT_TO_SERVE; 	break;
		case MOVING_LEFT: 		this.gameState = State.MOVING_RIGHT; 	break;
		case MOVING_RIGHT: 		this.gameState = State.MOVING_LEFT;		break;
		case TOUCHED_LEFT: 		this.gameState = State.TOUCHED_RIGHT; 	break;
		case RIGHT_TO_SERVE: 	this.gameState = State.LEFT_TO_SERVE;	break;
		case TOUCHED_RIGHT: 	this.gameState = State.TOUCHED_LEFT;	break;
		default: 				this.gameState = stateNet.getGameState();	break; 			 
		}
		
		
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getBallY() {
		return ballY;
	}

	public void setBallY(int ballY) {
		this.ballY = ballY;
	}

	public int getBallX() {
		return ballX;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

}
