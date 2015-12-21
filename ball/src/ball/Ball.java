package ball;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

import net.Game;



public class Ball extends JPanel  implements ActionListener {
	Timer t ;
	double x, y , velX, velY;
//	double a = 0, b = 0, velA = 2.6, velB = 1;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D circle = new Ellipse2D.Double(x,y,40,40);
//		Ellipse2D circle2 = new Ellipse2D.Double(a,b,40,40);
		g2.fill(circle);
//		g2.fill(circle2);		
//		System.out.println(x +"  "+  y);
		
	}
	
	
	
	public int getX() {
		return (int) x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public int getY() {
		return (int) y;
	}



	public void setY(double y) {
		this.y = y;
	}



	public Ball() {
		t = new Timer(30, this);
		x = 0;
		y = 0; 
		velX = 2; 
		velY = 2;
		t.start();
		//System.out.println(x +"  "+  y);
		
	
	}
	
	public Ball(Game game) {
		
	}
	
	public Ball(Game game, String host){
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (x < 0 || x > 560) velX = -velX;
		if (y < 0 || y > 340) velY = -velY;
		x += velX;
		y += velY;
		
		//System.out.println(x +"  "+  y);

//		if (a < 0 || a > 560) velA = -velA;
//		if (b < 0 || b > 340) velB = -velB;
//		a += velA;
//		b += velB;
		
		repaint();
	}
	

}
