package pong;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game extends JPanel implements ActionListener {
	Timer t = new Timer(5, this);
	Ball aBall = new Ball(500,50,2,2);
	RightBar rBar = new RightBar(175);
	LeftBar lBar = new LeftBar(175);
	public void  paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0,0, 600, 400);
		aBall.drawBall(g);
		rBar.drawBar(g);
		lBar.drawBar(g);
		t.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
