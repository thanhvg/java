package pong;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Ball {
	
	private int x, y, velX, velY;
	JPanel display;
	// enum status {MOVELEFT,STOPPED,MOVERIGHT} 
	BallStatus ballStatus;
	//Graphics g;
	
	public void setX(int x) { this.x = x;}
	public void setY(int y) { this.y = y;}
	public void setVelX(int vel) { this.velX = vel;}
	public void setVelY(int vel) { this.velY = vel;}
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getVelX() {return this.velX;}
	public int getVelY() {return this.velY;}
	public int[] getPos() {
		int[] b = {this.x,this.y}; 
		return  b;
		}
	
	public BallStatus getStatus() {
		return ballStatus;
	}
	
	public void setStatus(BallStatus ballStatus){
		this.ballStatus = ballStatus;
	}
	
	public Ball(int x, int y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		ballStatus = BallStatus.MOVELEFT;
	}

	public Ball(int x, int y, int velX, int velY, JPanel display) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.display = display;
		
	}

	
//now the draw 
	public void drawBall(Graphics g) { 
		g.fillOval(x,y,8,8);     	
		
		
	}
	
	public void bounce() {
		velX = -velX;
		//ballStatus = ballStatus.flip();
	}
	
	public void move() {
		
		if (y < 0 || y > 390) velY = -velY;
		x += velX;
		y += velY;
	}
	
	/* @Override
	public void run() {		
		//drawBall(g);
		//you don't draw anything here, you just do the calculation
		while (true) {
			if (x < 0 || x > 590) velX = -velX;
			if (y < 0 || y > 390) velY = -velY;
			x += velX;
			y += velY;
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// display.repaint();
			
		} */
		

}
