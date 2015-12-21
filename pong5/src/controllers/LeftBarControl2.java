package controllers;

import java.awt.event.KeyEvent;

import game.*;
import network2.Hub;
import sprites.*;

public class LeftBarControl2 implements InputFromPlayer {
	// Should this listen to events to change its action or just an if then to verify its action?
	BarLeft barLeft;
	int speed, y ;
	Hub hub;
	
	
	public LeftBarControl2(BarLeft barLeft, Hub hub) {
		super();
		this.barLeft = barLeft;
		this.hub = hub;
		speed = 0;
		y = barLeft.getY();
	}

	public void move() {
		y +=  speed;
		if (y < 0) {
			y = 0;
		}
		if (y > Constant.BOX_HEIGHT - Constant.BAR_HEIGHT) {
			y = Constant.BOX_HEIGHT - Constant.BAR_HEIGHT;
		}
		updateY();
	}
	
	void updateY() {
		barLeft.setY(y);
	}

	@Override
	public void keyPressedHandle(int key) {

		
		// int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			speed = -barLeft.getdY();
			//System.out.println("Inside LeftBarControl VK_UP speed: " + speed + " y: "+ y);
		}

		if (key == KeyEvent.VK_DOWN) {
			speed = barLeft.getdY();;
		}
		
		if (key == KeyEvent.VK_SPACE) {
			if (hub.getState() == State.LEFT_TO_SERVE) {
				hub.eventCenter(Event.BALL_SERVED);
				//System.out.println("Inside LeftBarControl VK_SPACE speed: " + speed);
			}
		}
		
	}

	@Override
	public void keyReleasedHandle(int key) {

		// int key = e.getKeyCode();


		if (key == KeyEvent.VK_UP) {
			speed = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			speed = 0;
		}
	}
	

}
