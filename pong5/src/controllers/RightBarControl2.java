package controllers;

import java.awt.event.KeyEvent;

import game.*;
import network2.Hub;
import sprites.*;

public class RightBarControl2 implements InputFromPlayer {
	// Should this listen to events to change its action or just an if then to verify its action?
	BarRight barRight;
	int speed, y ;
	Hub hub;
	
	
	public RightBarControl2(BarRight barRight, Hub hub) {
		super();
		this.barRight = barRight;
		this.hub = hub;
		speed = 0;
		y = barRight.getY();
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
		barRight.setY(y);
	}

	@Override
	public void keyPressedHandle(int key) {

		
		// int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			speed = -barRight.getdY();
			//System.out.println("Inside LeftBarControl VK_UP speed: " + speed + " y: "+ y);
		}

		if (key == KeyEvent.VK_DOWN) {
			speed = barRight.getdY();;
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
