package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JPanel;

import sprites.*;

public class Animator extends JPanel  {
	Ball ball;
	BarLeft barLeft;
	BarRight barRight;
	//Timer timer;
	public Animator(Ball ball, BarLeft barLeft, BarRight barRight) {
		super();
		this.ball = ball;
		this.barLeft = barLeft;
		this.barRight = barRight;
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		barLeft.draw(g);
		barRight.draw(g);
		ball.draw(g);
		//requestFocus();
	}
}
