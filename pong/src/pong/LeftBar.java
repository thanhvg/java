package pong;

public class LeftBar extends Bar {
	
	Ball ball;
	int speed = 4;
	
	public LeftBar (int y) {
		this.y = y;
	    this.x = 0;
	}
	
	public LeftBar (int y, Ball ball) {
		this.y = y;
	    this.x = 0;
	    this.ball = ball;
	}
	
	public void move(){
		if (ball.getStatus() == BallStatus.MOVELEFT) {
		// if (true){
			if (y > ball.getY()) {
				y -= speed ;
			} 
			if (y < ball.getY() && y < 350) {
				y += speed ;
			} 
			
		}
		// System.out.println("ball is " + ball.getStatus()); 
	}
	
	
	
}
