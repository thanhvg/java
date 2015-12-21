package pong;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Game2 extends JPanel implements Runnable, MouseMotionListener, KeyListener, ActionListener {
	
	Ball aBall = new Ball(500,50,-3,-3);
	RightBar rBar = new RightBar(175);
	LeftBar lBar = new LeftBar(175, aBall);
	public void  paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0,0, 600, 400);
		aBall.drawBall(g);
		rBar.drawBar(g);
		lBar.drawBar(g);
		requestFocus();
	}
	
	Thread worker = new Thread(this,"Ball Stuff");
	
	public void init()   {
			
		worker.start();
		
		addMouseMotionListener(this);
		addKeyListener(this);
		
			// ballThread.start();
			// ballThread.sleep(30);
			// repaint();
	
		
		
	}
	
	public void newGame() {
		
	}
	
	public void serveBall() {
		//BallStatus state = aBall.getStatus();
		System.out.println("Im here in front of serveBall " + aBall.getStatus() + " " + worker.isAlive());
		if ( aBall.getStatus() == BallStatus.STOPPED) {
			
			// aBall = new Ball(500,50,-4,-4);
			aBall.setVelX(-4);
			aBall.setVelY(-4);
			aBall.setX(500);
			aBall.setY(50);
			aBall.setStatus(BallStatus.MOVELEFT);
			
			
			System.out.println("Im here in serveBall " + aBall.getStatus() + " " + worker.getState());
			//worker.start();
			System.out.println(aBall.getVelX());
			//repaint();
		}
		
	}
	
	public void updateScore() {
	
	}
	
	public void run () {
		while (true) {
			//Set ballState
			BallStatus bState = aBall.getStatus();
			int x = aBall.getX();
			int y = aBall.getY();
			
		    
			if (x < 5 && bState == BallStatus.MOVELEFT) {
				if ((y - lBar.getPos()) < 58 && (y - lBar.getPos()) > -8 ) {
					aBall.bounce();
					aBall.setStatus(BallStatus.MOVERIGHT);
					//System.out.println("Im here 1 " + aBall.getStatus() + "  "+ x);
					// System.out.println("Im here 3 " + aBall.getStatus() + "delta" + (y-lBar.getPos()));
				} else { 
					//aBall.move();
					aBall.setStatus(BallStatus.STOPPED);
					aBall.setX(0);
					updateScore();
					//System.out.println("Im here 2 " + aBall.getStatus());
				}
				
			}
			
			if (x > 590 && bState == BallStatus.MOVERIGHT) {
				if ((y - rBar.getPos()) < 58 && (y - rBar.getPos()) > -8 ) {
					aBall.bounce();
					aBall.setStatus(BallStatus.MOVELEFT);
					// System.out.println("Im here 3 " + aBall.getStatus() + "delta" + (y-rBar.getPos()));
				} else { 
					//aBall.move();
					aBall.setStatus(BallStatus.STOPPED);
					aBall.setX(593);
					updateScore();
					// System.out.println("Im here 4 " + aBall.getStatus()+ "  "+x);
				}
				
			}
			
						    
		    //check bouncing 
			
		    // on going 
			if (aBall.getStatus() != BallStatus.STOPPED)  {
				aBall.move();
			}
			//System.out.println(aBall.getStatus());
			//System.out.println("Im here final " + aBall.getStatus()+ "  "+aBall.getX());
			lBar.move();
		    
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("Im here in serveBall 1");
		char key = e.getKeyChar();
		if (key == 's') { 
			System.out.println("Im here in keyPressed");
			serveBall();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int mY = e.getY()-25;
		int currentY = rBar.getPos();
		if (mY > currentY) {
			rBar.setPos(currentY+rBar.speed);
		} else if  (mY < currentY) {
			rBar.setPos(currentY-rBar.speed);
		}
		//repaint();
		
		
	}

}