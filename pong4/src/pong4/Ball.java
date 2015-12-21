package pong4;

import java.awt.Graphics;

public class Ball implements GameConstants {
	int x, y, speedX, speedY;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public void draw (Graphics g) {
		g.fillOval(x,y,BALL_SIZE,BALL_SIZE);
	}
	
	
	

}
