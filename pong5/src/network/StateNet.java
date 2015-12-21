package network;

import java.io.Serializable;

import game.*;

public class StateNet implements Serializable {
	/**
	 * This is from player 1 perspective 
	 */
	int p1; // player 1 y position
	int p2; // player 2 y position
	int ballY;
	int ballX;
	State gameState;
	
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
	public StateNet(int p1, int p2, int ballY, int ballX, State gameState) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.ballY = ballY;
		this.ballX = ballX;
		this.gameState = gameState;
	}
	
	
	
	
	

}
