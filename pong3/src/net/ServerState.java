package net;

import java.io.Serializable;

public class ServerState implements Serializable {
	int ballX, ballY;
	int p1Y;
	
	public ServerState(int ballX, int ballY, int p1y) {
		this.ballX = ballX;
		this.ballY = ballY;
		p1Y = p1y;
	}
	
	public void setState(int ballX, int ballY, int p1y) {
		this.ballX = ballX;
		this.ballY = ballY;
		p1Y = p1y;
	}
	 
	public int getBallX() {
		return ballX;
	}
	public void setBallX(int ballX) {
		this.ballX = ballX;
	}
	public int getBallY() {
		return ballY;
	}
	public void setBallY(int ballY) {
		this.ballY = ballY;
	}
	public int getP1Y() {
		return p1Y;
	}
	public void setP1Y(int p1y) {
		p1Y = p1y;
	}
	

}
