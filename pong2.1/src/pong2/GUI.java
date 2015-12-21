package pong2;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;
import javax.swing.*;

public class GUI  extends JPanel  implements GameConstants, ActionListener {
	Ball ball;
	Bar1 bar1;
	Bar2 bar2;
	Timer timer;
	GUIstate state;
	private int serveCount;

	public void setState(GUIstate state) {
		this.state = state;
	}

	public GUIstate getState() {
		return state;
	}

	public void init() {
		ball = new Ball((BOX_WIDTH - BAR_WIDTH - BALL_SIZE),(BOX_HEIGHT/2), 7, 3, BallState.TOSERVE, this);
		bar1 = new Bar1(0,(BOX_HEIGHT/2 - BAR_HEIGHT/2),0,3,this);
		//System.out.println(BOX_HEIGHT/2 - BAR_HEIGHT/2);
		bar2 = new Bar2(BOX_WIDTH-BAR_WIDTH,(BOX_HEIGHT/2 - BAR_HEIGHT/2),0,4,this);
		serveCount = 0;

	}

//	public void serveBall() {
//		if (ball.getState() == BallState.TOSERVE && state == GUIstate.SERVE) {
//			//ball sticks with bar2 
//			ball.saveSpec();
//			ball.setY(bar2.getdY());
//			ball.setX(BOX_WIDTH - BAR_WIDTH);
//			ball.setdX(0);
//			ball.setdY(bar2.getdY());
//
//		}
//	}

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			bar2.keyReleased(e);
			//System.out.println("hi");	
//			if (ball.getState() == BallState.TOSERVE && state == GUIstate.SERVE) {
//				ball.keyReleased(e);
//			}
		}

		public void keyPressed(KeyEvent e) {
			bar2.keyPressed(e);

//			if (ball.getState() == BallState.TOSERVE && state == GUIstate.SERVE) {
//				ball.keyReleased(e);
//			}
			int key = e.getKeyCode();
			//System.out.println(key);
			if (key == 83) {
				++serveCount;
				System.out.printf("Servecount  %d\n", serveCount);
				if (serveCount == 2) {
					ball.setState(BallState.SERVE);
					//System.out.println(ball.spec);					
					state = GUIstate.PLAYING;
					serveCount = 0;
				}
				if (serveCount == 1) {
					//ball.saveSpec();
					//serveBall();
					ball.setState(BallState.TOSERVE);
					state = GUIstate.SERVE;
				}
			}

		}
	} // end of adapter

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bar1.draw(g);
		bar2.draw(g);
		ball.draw(g);
		//requestFocus();
	}

	public GUI() {
		init();
		state = GUIstate.SERVE;
		//ball.serve(bar1.getY(),bar2.getY());
		setFocusable(true);
		setSize(BOX_WIDTH,BOX_HEIGHT);
		addKeyListener(new TAdapter());
		timer = new Timer(30, this);
		timer.start();
        
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ball.move(bar1.getY(),bar2.getY());
		bar1.move(ball.getX(),ball.getY(),ball.getState());
		bar2.move();
		repaint();
	}
}
